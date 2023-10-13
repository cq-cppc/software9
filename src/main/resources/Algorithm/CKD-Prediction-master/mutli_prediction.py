import pandas as pd
import mysql.connector
import pickle
import json

# 加载模型文件
with open('F:\Code\CKD-Prediction-master\model_gini.pkl', 'rb') as f:
    model_gini = pickle.load(f)

with open('F:\Code\CKD-Prediction-master\model_entropy.pkl', 'rb') as f:
    model_entropy = pickle.load(f)

# 定义函数用于批量预测并保存结果到 MySQL
def batch_prediction(table_name):
    # 连接 MySQL 数据库
    connection = mysql.connector.connect(host='localhost', user='root', password='123456', database='ckd', auth_plugin='mysql_native_password')

    # 读取原始表数据
    query = f"SELECT * FROM {table_name}"
    original_data = pd.read_sql(query, connection)

    # 提取特征列
    features = original_data.iloc[:, :]

    # 使用模型进行批量预测
    prediction_gini = model_gini.predict(features)
    prediction_entropy = model_entropy.predict(features)

    #使用模型进行批量预测获得患病概率
    prediction_gini_proba = model_gini.predict_proba(features)[:, 1]
    prediction_entropy_proba = model_entropy.predict_proba(features)[:, 1]
    prediction_gini_proba_format = ["{:.3f}".format(prob) for prob in prediction_gini_proba]  # 保留三位小数
    prediction_entropy_proba_format = ["{:.3f}".format(prob) for prob in prediction_entropy_proba]

    # 将预测结果转换为数值类型
    prediction_gini = [1 if pred == 'ckd' else 0 for pred in prediction_gini]
    prediction_entropy = [1 if pred == 'ckd' else 0 for pred in prediction_entropy]

    # 计算患病比例
    disease_ratio_gini = sum(prediction_gini) / len(prediction_gini)
    disease_ratio_entropy = sum(prediction_entropy) / len(prediction_entropy)
    disease_ratio_gini_format= "{:.3f}".format(disease_ratio_gini)
    disease_ratio_entropy_format="{:.3f}".format(disease_ratio_entropy)

    # 将预测结果和原始数据合并为 DataFrame
    results = pd.concat([pd.Series(prediction_gini_proba_format, name='disease_probability'),
                         pd.Series(prediction_gini, name='predicted_target'),
                         # pd.Series(prediction_entropy_proba_format, name='Prediction (Entropy)'),
                         original_data], axis=1)

    # 生成结果表名
    result_table_name = f"{table_name}_result"

    # 创建数据库游标
    cursor = connection.cursor()
    # 检查结果表是否存在，如果存在则删除
    cursor.execute(f"DROP TABLE IF EXISTS {result_table_name}")

    # 创建结果表
    column_names = results.columns.tolist()
    column_definitions = ['`' + name + '` TEXT' for name in column_names]
    create_table_query = f"CREATE TABLE {result_table_name} ({', '.join(column_definitions)})"
    cursor.execute(create_table_query)

    # 将结果插入到结果表中
    for _, row in results.iterrows():
        values = tuple(row)
        insert_query = f"INSERT INTO {result_table_name} VALUES {values}"
        cursor.execute(insert_query)

    # 提交事务
    connection.commit()

    # 关闭游标和数据库连接
    cursor.close()
    connection.close()

    results_info = [
        {"result_table_name": result_table_name},
        {"disease_ratio_gini":disease_ratio_gini_format},
        # {"disease_ratio_entropy":disease_ratio_entropy_format}
    ]

    json_output = json.dumps(results_info)
    print(json_output)



import argparse

# 创建参数解析器
parser = argparse.ArgumentParser()
parser.add_argument("--tableName", type=str, default=None)

# 解析命令行参数
args = parser.parse_args()
tableName=args.tableName
# 调用函数进行批量预测并保存结果到 MySQL
batch_prediction(tableName)
