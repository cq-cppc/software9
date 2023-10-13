import argparse
import json

import mysql.connector
import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split, cross_val_score
from sklearn.ensemble import RandomForestClassifier
from sklearn.preprocessing import StandardScaler
from sklearn.ensemble import RandomForestClassifier
from sklearn.feature_selection import SelectFromModel
import joblib

def load_model(model_file_path):
    # 加载训练好的模型
    model = joblib.load(model_file_path)
    return model

def feature_standardization(X):
    scaler = StandardScaler()
    standardized_X = scaler.fit_transform(X)

    return standardized_X
def preprocess_new_data(X):
    # 在预测新数据之前，需要进行与训练数据相同的预处理操作
    # 假设我们对新数据也进行特征标准化和特征选择
    X = feature_standardization(X)
    return X

def make_predictions_proba(model, X):
    # 使用加载的模型进行预测，并获取预测概率
    probabilities = model.predict_proba(X)
    return probabilities

if __name__ == "__main__":

    parser = argparse.ArgumentParser()
    parser.add_argument("--model_file_path", type=str, default=None)

    parser.add_argument("--feature", nargs='+', type=str, default=None)
    args = parser.parse_args()
    # 加载训练好的模型
    # model_file_path = 'trained_model.pkl'
    model_file_path = args.model_file_path


    loaded_model = load_model(model_file_path)

    # 假设有新的数据需要预测，将新数据组织成一个DataFrame或二维数组
    # new_data = np.array([[2,1,2013,3,4,5,68,2,2,16,1,70,40,10,0,50,5,0,3,0,1,1,3,1,0,0]])  # 填写新数据的特征值
    # feature = args.feature
    # input_string = feature[0]
    # numbers_list = input_string.split()
    # idxs = [float(num) for num in numbers_list]
    # feature = [[idxs]]
    # # 对新数据进行预处理
    # preprocessed_new_data = preprocess_new_data(feature)
    feature = args.feature[0].split()
    idxs = [float(num) for num in feature]
    preprocessed_new_data = preprocess_new_data([idxs])

    # 使用加载的模型进行预测
    predictions = make_predictions_proba(loaded_model, preprocessed_new_data)[:, 1]
    # result = [{"分类概率：", predictions}]
    # json_out = json.dumps(result)
    # print(json_out)
    result = [{"分类概率": predictions.tolist()}]
    json_out = json.dumps(result)
    print(json_out)
