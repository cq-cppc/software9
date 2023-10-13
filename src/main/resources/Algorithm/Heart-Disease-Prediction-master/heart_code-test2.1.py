import json
import pickle
import random
from urllib import request
import mysql.connector
import pandas as pd

# import warnings filter
from warnings import simplefilter

from docutils.nodes import classifier




# ignore all future warnings
simplefilter(action='ignore', category = FutureWarning)
# import warnings filter
from warnings import simplefilter
# ignore all future warnings
simplefilter(action='ignore', category = FutureWarning)


df = pd.read_csv('E:\software9\src\main\resources\Algorithm\Heart-Disease-Prediction-master\cleveland.csv', header = None)

df.columns = ['age', 'sex', 'cp', 'trestbps', 'chol',
              'fbs', 'restecg', 'thalach', 'exang', 
              'oldpeak', 'slope', 'ca', 'thal', 'target']


df.isnull().sum()

df['target'] = df.target.map({0: 0, 1: 1, 2: 1, 3: 1, 4: 1})
df['sex'] = df.sex.map({0: 'female', 1: 'male'})
df['thal'] = df.thal.fillna(df.thal.mean())
df['ca'] = df.ca.fillna(df.ca.mean())



df['sex'] = df.sex.map({'female': 0, 'male': 1})


################################## data preprocessing
X = df.iloc[:, :-1].values
y = df.iloc[:, -1].values

from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler as ss, StandardScaler

scaler = StandardScaler()
X_scaled = scaler.fit_transform(X)



from xgboost import XGBClassifier
xg = XGBClassifier()
xg.fit(X_scaled, y)



def get_mysql_data_type(data):
    if data.dtype == 'int64':
        return 'INT'
    elif data.dtype == 'float64':
        return 'FLOAT'
    elif data.dtype == 'bool':
        return 'BOOL'
    else:
        return 'VARCHAR(255)'


import pandas as pd
import numpy as np

def preprocess_data(data):
    # Preprocess the batch data
    # data['sex'] = data.sex.map({'female': 0, 'male': 1})
    # print(data['sex'])
    data['thal'] = data.thal.fillna(data.thal.mean())
    data['ca'] = data.ca.fillna(data.ca.mean())
    return data

def predict_batch_data(table_name):
    # MySQL database connection parameters
    db_host = '10.16.48.219'
    db_user = 'root'
    db_password = '111111'
    db_name = 'software9'

    # Connect to MySQL database
    conn = mysql.connector.connect(
        host=db_host,
        user=db_user,
        password=db_password,
        database=db_name
    )

    # Get column names from the table
    query = f"DESCRIBE {table_name}"
    cursor = conn.cursor()
    cursor.execute(query)
    columns = [col[0] for col in cursor.fetchall()]
    cursor.close()

    # Read the batch data from the MySQL table
    query = f"SELECT * FROM {table_name}"
    batch_data = pd.read_sql(query, conn)

    batch_data = preprocess_data(batch_data)
    # print(batch_data)

    X_test = batch_data[columns].values

    # Check if the number of features matches the model's expectations
    if X_test.shape[1] != 13:
        raise ValueError("The number of features in X does not match the model's expectations.")

    # Predict the target for the batch data
    y_pred = xg.predict(X_test)
    y_pred_prob = xg.predict_proba(X_test)[:, 1]  # 获取患病概率值

    # Calculate the percentage of patients with heart disease
    disease_percentage = sum(y_pred) / len(y_pred)

    # Add a new column to the batch data indicating whether each sample has heart disease or not
    # batch_data.insert(0, 'disease_probability', y_pred_prob)

    # disease_probability_rounded = np.round(y_pred_prob, 3)  # 保留三位小数
    # batch_data.insert(0, 'disease_probability', disease_probability_rounded)

    disease_probability_formatted = ["{:.3f}".format(prob) for prob in y_pred_prob]  # 保留三位小数
    # # 将字符串格式化的列表转换为浮点数列表
    # disease_probabilities = [float(prob) for prob in disease_probability_formatted]
    #
    # # 生成随机数
    # random_number = random.uniform(0, 1)
    #
    # # 将随机数与浮点数列表中的每个元素相加
    # result = [min(max(prob + random_number, 0), 1) for prob in disease_probabilities]

    # 将保留三位小数后的患病概率值插入结果表
    batch_data.insert(0, 'disease_probability', disease_probability_formatted)
    batch_data.insert(1, 'predicted_target', y_pred)
    # Save the prediction results to a new MySQL table
    output_table_name = f"{table_name}_result"

    # Drop the table if it already exists
    cursor = conn.cursor()
    cursor.execute(f"DROP TABLE IF EXISTS {output_table_name}")
    conn.commit()

    # Create the table if it does not exist
    cursor = conn.cursor()

    # Prepare column names and data types for creating the table
    column_names = batch_data.columns.tolist()
    columns_with_target_and_types = [f"{col} {get_mysql_data_type(batch_data[col])}" for col in column_names]

    create_table_query = f"CREATE TABLE IF NOT EXISTS {output_table_name} ({', '.join(columns_with_target_and_types)})"
    cursor.execute(create_table_query)
    conn.commit()

    # Insert the prediction results into the table
    batch_data = batch_data.replace({None: np.nan, np.nan: None})
    # print(batch_data)
    cursor.executemany(f"INSERT INTO {output_table_name} VALUES ({', '.join(['%s']*len(column_names))})", batch_data.values.tolist())

    conn.commit()
    results = [
        {"Output table name": output_table_name},
        {"disease_percentage": disease_percentage}
    ]

    json_output = json.dumps(results)
    print(json_output)
    # Close the database connection
    conn.close()



#
# import os
# #
# # # Get the table name from environment variable
# table_name = os.getenv('TABLE_NAME')
#
#
# if table_name is None:
#     raise ValueError("Please set the TABLE_NAME environment variable.")

import argparse

# 创建参数解析器
parser = argparse.ArgumentParser()
parser.add_argument("--tableName", type=str, default=None)

# 解析命令行参数
args = parser.parse_args()
tableName=args.tableName


# 调用 predict_batch_data 函数，并传递解析得到的参数
predict_batch_data(tableName)


