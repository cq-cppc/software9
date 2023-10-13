import argparse
import json
import datetime
import os
import re

import mysql.connector
import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split, cross_val_score
from sklearn.preprocessing import StandardScaler
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score, precision_score, recall_score, f1_score, roc_auc_score, roc_curve, auc
import matplotlib.pyplot as plt
import joblib

# 假设你的数据库配置信息，可以根据实际情况进行修改

def read_data_from_mysql(tableName,target,feature):
    # 假设你已经获得了数据库的连接参数，例如：host、user、password、database等
    # 请根据你自己的实际情况来填写这些参数
    host = "localhost"
    user = "root"
    password = "123456"
    database = "software9_disease"
    # 连接到MySQL数据库
    connection = mysql.connector.connect(host=host, user=user, password=password, database=database)
    # 读取数据到DataFrame
    query = f"SELECT * FROM {tableName}"
    data = pd.read_sql(query, connection)
    # 关闭数据库连接
    connection.close()
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


# # 特征选择：使用L1正则化进行特征筛选
# def feature_selection(X, y):
#     clf = RandomForestClassifier(n_estimators=100, random_state=42)
#     clf.fit(X, y)
#     model = SelectFromModel(clf, prefit=True)
#     selected_X = model.transform(X)
#
#     return selected_X


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

    # X = feature_selection(X,y)


    # 使用十字交叉验证划分训练集和测试集
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

    # 训练随机森林模型
    model = train_random_forest(X_train, y_train, n_estimators, max_depth, min_samples_split, min_samples_leaf,
                                max_features, bootstrap)

    # # 保存训练好的模型到.pkl文件
    # model_file_path = 'trained_model.pkl'
    # joblib.dump(model, model_file_path)

    # current_time = datetime.datetime.now().strftime("%Y%m%d_%H%M%S")
    # model_file_path = f'trained_model_{current_time}.pkl'
    # joblib.dump(model, model_file_path)

    # 保存训练好的模型到指定地址
    model_folder_path = r"F:\Code\Online training model"
    if not os.path.exists(model_folder_path):
        os.makedirs(model_folder_path)

    current_time = datetime.datetime.now().strftime("%Y%m%d_%H%M%S")
    model_file_path = os.path.join(model_folder_path, f'trained_model_{current_time}.pkl')
    joblib.dump(model, model_file_path)

    # 计算训练集指标
    y_train_pred = model.predict(X_train)
    y_train_prob = model.predict_proba(X_train)[:, 1]
    train_total_samples = len(y_train)
    train_positive_samples = sum(y_train)
    train_accuracy = accuracy_score(y_train, y_train_pred)
    train_precision = precision_score(y_train, y_train_pred)
    train_recall = recall_score(y_train, y_train_pred)
    train_f1_score = f1_score(y_train, y_train_pred)
    train_auc = roc_auc_score(y_train, y_train_prob)
    fpr_train, tpr_train, _ = roc_curve(y_train, y_train_prob)
    train_auc_ci = auc(fpr_train, tpr_train)

    # 计算测试集指标
    y_test_pred = model.predict(X_test)
    y_test_prob = model.predict_proba(X_test)[:, 1]
    test_total_samples = len(y_test)
    test_positive_samples = sum(y_test)
    test_accuracy = accuracy_score(y_test, y_test_pred)
    test_precision = precision_score(y_test, y_test_pred)
    test_recall = recall_score(y_test, y_test_pred)
    test_f1_score = f1_score(y_test, y_test_pred)
    test_auc = roc_auc_score(y_test, y_test_prob)
    test_auc = '{:.3f}'.format(test_auc)
    fpr_test, tpr_test, _ = roc_curve(y_test, y_test_prob)
    test_auc_ci = auc(fpr_test, tpr_test)
    test_auc_ci = '{:.3f}'.format(test_auc_ci)

    # 输出训练集和测试集指标
    # print("训练集：")
    # print("样本总量：", train_total_samples)
    # print("正样本量：", train_positive_samples)
    # print("准确度：", train_accuracy)
    # print("精确度：", train_precision)
    # print("召回率：", train_recall)
    # print("F1分数：", train_f1_score)
    # print("AUC：", train_auc)
    # print("AUC_CI：", train_auc_ci)
    #
    # print("测试集：")
    # print("样本总量：", test_total_samples)
    # print("正样本量：", test_positive_samples)
    # print("准确度：", test_accuracy)
    # print("精确度：", test_precision)
    # print("召回率：", test_recall)
    # print("F1分数：", test_f1_score)
    # print("AUC：", test_auc)
    # print("AUC_CI：", test_auc_ci)

    # # 绘制ROC曲线
    # plt.figure()
    # plt.plot(fpr_train, tpr_train, color='blue', label='Train ROC curve (area = %0.2f)' % train_auc)
    # plt.plot(fpr_test, tpr_test, color='red', label='Test ROC curve (area = %0.2f)' % test_auc)
    # plt.plot([0, 1], [0, 1], color='gray', linestyle='--')
    # plt.xlabel('False Positive Rate')
    # plt.ylabel('True Positive Rate')
    # plt.title('ROC Curve')
    # plt.legend(loc="lower right")
    # plt.show()
    #
    # # 绘制决策曲线
    # plt.figure()
    # plt.plot(train_recall, train_precision, color='blue', label='Train Decision Curve')
    # plt.plot(test_recall, test_precision, color='red', label='Test Decision Curve')
    # plt.xlabel('Recall')
    # plt.ylabel('Precision')
    # plt.title('Decision Curve')
    # plt.legend(loc='lower left')
    # plt.show()

    # 计算训练集和测试集的ROC曲线
    # fpr_train, tpr_train, _ = roc_curve(y_train, y_train_prob)
    # train_auc = auc(fpr_train, tpr_train)
    #
    # fpr_test, tpr_test, _ = roc_curve(y_test, y_test_prob)
    # test_auc = auc(fpr_test, tpr_test)

    # # 将ROC曲线相关数据存储到字典中
    # result_roc = {
    #     "train_fpr": fpr_train.tolist(),
    #     "train_tpr": tpr_train.tolist(),
    #     "train_auc": train_auc,
    #     "test_fpr": fpr_test.tolist(),
    #     "test_tpr": tpr_test.tolist(),
    #     "test_auc": test_auc
    # }
    #
    # # 将训练集和测试集的结果数据存储到字典中
    # result_train = {
    #     "训练集": {
    #         "样本总量": train_total_samples,
    #         "正样本量": train_positive_samples,
    #         "精确度": train_precision,
    #         "准确度": train_accuracy,
    #         "召回率": train_recall,
    #         "F1分数": train_f1_score,
    #         "AUC": train_auc,
    #         "AUC_CI": train_auc_ci
    #     }
    # }
    #
    # result_test = {
    #     "测试集": {
    #         "样本总量": test_total_samples,
    #         "正样本量": test_positive_samples,
    #         "精确度": test_precision,
    #         "准确度": test_accuracy,
    #         "召回率": test_recall,
    #         "F1分数": test_f1_score,
    #         "AUC": test_auc,
    #         "AUC_CI": test_auc_ci
    #     }
    # }
    #
    # # 将结果路径存储到字典中
    # result_path = {
    #     "模型文件保存地址": model_file_path
    # }
    #
    # # 将所有结果字典合并到一个大字典中
    # result_dict =[
    #     {"ROC曲线相关数据": result_roc},
    #     { "训练集结果": result_train},
    #     {"测试集结果": result_test},
    #     {"结果保存路径": result_path}
    #    ]
    # json_output = json.dumps(result_dict)
    #
    # print(json_output)

    #修改一
#     # 将ROC曲线相关数据存储到字典中
#     result_roc = [{
#         "train_fpr": fpr_train.tolist(),
#         "train_tpr": tpr_train.tolist(),
#         "train_auc": train_auc,
#         "test_fpr": fpr_test.tolist(),
#         "test_tpr": tpr_test.tolist(),
#         "test_auc": test_auc
#     }
# ]
#     # 将训练集和测试集的结果数据存储到字典中
#     result_train = [
#        {"TotalSampleSize": train_total_samples},
#         {"PositiveSampleSize": train_positive_samples},
#         {"accuracy": train_precision},
#         {"precision": train_accuracy},
#         {"recall": train_recall},
#         {"F1": train_f1_score},
#         {"AUC": train_auc},
#         {"AUC_CI": train_auc_ci}
#     ]
#     result_test = [
#         {"TotalSampleSize": test_total_samples},
#         {"PositiveSampleSize": test_positive_samples},
#         {"accuracy": test_precision},
#         {"precision": test_accuracy},
#         {"recall": test_recall},
#         {"F1": test_f1_score},
#         {"AUC": test_auc},
#         {"AUC_CI": test_auc_ci}
#     ]
#
#     # 将结果路径存储到字典中
#     result_path = [{
#         "path": model_file_path
#     }]
#
#
#     json_output_train = json.dumps(result_train, ensure_ascii=False)
#     json_output_test = json.dumps(result_test, ensure_ascii=False)
#     json_output_path = json.dumps(result_path, ensure_ascii=False)
#
#
#     print(json_output_train)
#     print(json_output_test)
#     print(json_output_path)


    result_train = {
        "TotalSampleSize": train_total_samples,
        "PositiveSampleSize": train_positive_samples,
        "accuracy": train_precision,
        "precision": train_accuracy,
        "recall": train_recall,
        "F1": train_f1_score,
        "AUC": train_auc,
        "AUC_CI": train_auc_ci
    }

    result_test = {
        "TotalSampleSize": test_total_samples,
        "PositiveSampleSize": test_positive_samples,
        "accuracy": test_precision,
        "precision": test_accuracy,
        "recall": test_recall,
        "F1": test_f1_score,
        "AUC": test_auc,
        "AUC_CI": test_auc_ci
    }

    # 将结果路径存储到字典中
    result_path = {
        "path": model_file_path
    }

    # 将上述三个字典合并为一个整体的字典，并放在一个列表中
    result_list = [result_train, result_test, result_path]

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
