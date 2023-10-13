import argparse
import json
import datetime
import os
import re

import pymysql
import mysql.connector
import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split, cross_val_score
from sklearn.preprocessing import StandardScaler
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score, precision_score, recall_score, f1_score, roc_auc_score, roc_curve, auc

import joblib
from sqlalchemy import create_engine

# 假设你的数据库配置信息，可以根据实际情况进行修改

def read_data_from_mysql(tableName,target,feature):

    host = "10.16.48.219"
    user = "root"
    password = "111111"
    database = "software9"

    # 创建MySQL连接字符串
    conn_str = f"mysql+mysqlconnector://{user}:{password}@{host}/{database}"
    engine = create_engine(conn_str)
    # 读取数据到DataFrame

    # 创建MySQL连接字符串
    # conn_str = f"mysql+mysqlconnector://{user}:{password}@{host}/{database}"
    # engine = create_engine(conn_str)
    query = f"SELECT * FROM {tableName}"
    data = pd.read_sql(query, engine)
    # 关闭数据库连接
    # engine.close()
    engine.dispose()
    # 分离特征和目标列
    # 分离特征和目标列
    X = data.iloc[:, feature]
    y = data.iloc[:, target]

    return X, y


# 特征标准化：使用z_score进行特征缩放
def feature_standardization(X):
    scaler = StandardScaler()
    standardized_X = scaler.fit_transform(X)

    return standardized_X




def train_random_forest(X_train, y_train, n_estimators, max_depth, min_samples_split, min_samples_leaf, max_features,
                        bootstrap):
    # 创建随机森林分类器，并根据超参数进行配置
    clf = RandomForestClassifier(n_estimators=n_estimators, max_depth=max_depth, min_samples_split=min_samples_split,
                                 min_samples_leaf=min_samples_leaf, max_features=max_features, bootstrap=bootstrap)

    # 使用十字交叉验证进行模型评估
    cv_scores = cross_val_score(clf, X_train, y_train, cv=10)
    # print("交叉验证准确率：", np.mean(cv_scores))
    # 训练模型
    clf.fit(X_train, y_train)

    return clf


def main(table_name,target,feature, n_estimators, max_depth, min_samples_split, min_samples_leaf, max_features, bootstrap):
    # 从数据库中读取数据
    X, y = read_data_from_mysql(table_name,target,feature)

    # 数据预处理
    X = feature_standardization(X)




    # 使用十字交叉验证划分训练集和测试集
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

    # 训练随机森林模型
    model = train_random_forest(X_train, y_train, n_estimators, max_depth, min_samples_split, min_samples_leaf,
                                max_features, bootstrap)



    # 保存训练好的模型到指定地址
    model_folder_path = r"E:\softdevelop\mode"
    if not os.path.exists(model_folder_path):
        os.makedirs(model_folder_path)

    current_time = datetime.datetime.now().strftime("%Y%m%d_%H%M%S")
    model_file_path = os.path.join(model_folder_path, f'trained_model_{current_time}.pkl')
    joblib.dump(model, model_file_path)


    # 将结果路径存储到字典中
    result_path = {
        "path": model_file_path
    }

    # 将上述三个字典合并为一个整体的字典，并放在一个列表中
    result_list = [result_path]

    json_output = json.dumps(result_list, ensure_ascii=False)

    print(json_output)


if __name__ == "__main__":

    parser = argparse.ArgumentParser()
    parser.add_argument("--tableName", type=str, default=None)
    parser.add_argument("--target", type=int, default=None)
    parser.add_argument("--n_estimators", type=int, default=None)
    parser.add_argument("--min_samples_leaf", type=int, default=None)
    parser.add_argument("--min_samples_split", type=int, default=None)
    parser.add_argument("--max_features",type=str,default=None)
    parser.add_argument("--bootstrap",type=bool,default=None)
    parser.add_argument("--feature", nargs='+', type=str, default=None)
    args = parser.parse_args()
    # table_name = 'gastriccancer'  # 填写你的数据表名
    table_name=args.tableName

    # target = 38
    target = args.target
    target = target-1

    # n_estimators = 100  # 填写超参数n_estimators
    n_estimators = args.n_estimators
    max_depth = None  # 填写超参数max_depth，设为None表示树深度不限制
    # min_samples_split = 2  # 填写超参数min_samples_split
    min_samples_split = args.min_samples_split
    # min_samples_leaf = 1  # 填写超参数min_samples_leaf
    min_samples_leaf = args.min_samples_leaf
    # max_features = 'auto'  # 填写超参数max_features，设为'auto'表示使用全部特征
    max_features = args.max_features
    # bootstrap = True  # 填写超参数bootstrap，设为True表示使用Bootstrap采样
    bootstrap = args.bootstrap

    # feature = [5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28]
    fea = args.feature
    # 字符串传化数组
    input_string = fea[0]
    numbers_list = input_string.split()
    idxs = [int(num) for num in numbers_list]
    feature = []
    for x in idxs:
        feature.append(x - 1)
    main(table_name,target,feature, n_estimators, max_depth, min_samples_split, min_samples_leaf, max_features, bootstrap)
