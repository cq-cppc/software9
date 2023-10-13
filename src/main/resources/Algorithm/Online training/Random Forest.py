import mysql.connector
import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split, cross_val_score
from sklearn.ensemble import RandomForestClassifier
from sklearn.preprocessing import StandardScaler
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score, precision_score, recall_score, f1_score, roc_auc_score, roc_curve, auc
import joblib
from sqlalchemy import create_engine

# 假设你的数据库配置信息，可以根据实际情况进行修改

def read_data_from_mysql(tableName,target,feature):
    # 假设你已经获得了数据库的连接参数，例如：host、user、password、database等
    # 请根据你自己的实际情况来填写这些参数
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
    print("交叉验证准确率：", np.mean(cv_scores))
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

    # 保存训练好的模型到.pkl文件
    model_file_path = 'trained_model.pkl'
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
    fpr_test, tpr_test, _ = roc_curve(y_test, y_test_prob)
    test_auc_ci = auc(fpr_test, tpr_test)

    # 输出训练集和测试集指标
    print("训练集：")
    print("样本总量：", train_total_samples)
    print("正样本量：", train_positive_samples)
    print("准确度：", train_accuracy)
    print("精确度：", train_precision)
    print("召回率：", train_recall)
    print("F1分数：", train_f1_score)
    print("AUC：", train_auc)
    print("AUC_CI：", train_auc_ci)

    print("测试集：")
    print("样本总量：", test_total_samples)
    print("正样本量：", test_positive_samples)
    print("准确度：", test_accuracy)
    print("精确度：", test_precision)
    print("召回率：", test_recall)
    print("F1分数：", test_f1_score)
    print("AUC：", test_auc)
    print("AUC_CI：", test_auc_ci)


if __name__ == "__main__":
    table_name = 'gastriccancer'  # 填写你的数据表名
    target = 38
    feature = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25]
    n_estimators = 100  # 填写超参数n_estimators
    max_depth = None  # 填写超参数max_depth，设为None表示树深度不限制
    min_samples_split = 2  # 填写超参数min_samples_split
    min_samples_leaf = 1  # 填写超参数min_samples_leaf
    max_features = 'auto'  # 填写超参数max_features，设为'auto'表示使用全部特征
    bootstrap = True  # 填写超参数bootstrap，设为True表示使用Bootstrap采样

    main(table_name,target,feature, n_estimators, max_depth, min_samples_split, min_samples_leaf, max_features, bootstrap)

