import json
import pickle
import numpy as np
import pandas as pd
import argparse
import shap

# import warnings filter
from warnings import simplefilter




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

### 1 = male, 0 = female
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
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.2, random_state = 0)

from sklearn.preprocessing import StandardScaler as ss
sc = ss()
X_train = sc.fit_transform(X_train)
X_test = sc.transform(X_test)

from xgboost import XGBClassifier
xg = XGBClassifier()
xg.fit(X_train, y_train)


# y_pred = xg.predict(X_test)
#
# from sklearn.metrics import confusion_matrix
# cm_test = confusion_matrix(y_pred, y_test)
#
# y_pred_train = xg.predict(X_train)
#
# for i in range(0, len(y_pred_train)):
#     if y_pred_train[i]>= 0.5:       # setting threshold to .5
#        y_pred_train[i]=1
#     else:
#        y_pred_train[i]=0
#
# cm_train = confusion_matrix(y_pred_train, y_train)
# print()
# print('Accuracy for training set for XGBoost = {}'.format((cm_train[0][0] + cm_train[1][1])/len(y_train)))
# print('Accuracy for test set for XGBoost = {}'.format((cm_test[0][0] + cm_test[1][1])/len(y_test)))
# model_filename = 'F:\Code\Heart-Disease-Prediction-master\model.pkl'
# pickle.dump(classifier, open(model_filename, 'wb'))

# 从控制台接收单例样本数据
# age = float(input("请输入年龄："))
# sex = input("请输入性别（female/male）：")
# cp = float(input("请输入胸痛类型："))
# trestbps = float(input("请输入静息血压："))
# chol = float(input("请输入胆固醇："))
# fbs = float(input("请输入空腹血糖："))
# restecg = float(input("请输入静息心电图结果："))
# thalach = float(input("请输入最大心率："))
# exang = float(input("请输入运动诱发心绞痛："))
# oldpeak = float(input("请输入运动引起ST段的压低："))
# slope = float(input("请输入ST段的斜率："))
# ca = float(input("请输入主要血管数："))
# thal = float(input("请输入缺陷的类型："))




parser = argparse.ArgumentParser()
parser.add_argument("--age", type=str, default=None)
parser.add_argument("--sex", type=str, default=None)
parser.add_argument("--cp", type=str, default=None)
parser.add_argument("--trestbps", type=str, default=None)
parser.add_argument("--chol", type=str, default=None)
parser.add_argument("--fbs", type=str, default=None)
parser.add_argument("--restecg", type=str, default=None)
parser.add_argument("--thalach", type=str, default=None)
parser.add_argument("--exang", type=str, default=None)
parser.add_argument("--oldpeak", type=str, default=None)
parser.add_argument("--slope", type=str, default=None)
parser.add_argument("--ca", type=str, default=None)
parser.add_argument("--thal", type=str, default=None)


args = parser.parse_args()
# # 将性别转换为数值表示
if args.sex == 'female':
    args.sex = 0
else:
    args.sex = 1



# 构建单例样本
instance = [[args.age, args.sex, args.cp, args.trestbps, args.chol, args.fbs, args.restecg, args.thalach, args.exang, args.oldpeak, args.slope, args.ca, args.thal]]
scaler = ss()
scaler.fit(X_train)
instance = scaler.transform(instance)  # 特征缩放





# 预测单例样本
prediction = xg.predict(instance)
#预测单例样本的概率
prediction_proba = xg.predict_proba(instance)

# 获取患病的概率（类别1的概率）
disease_probability = prediction_proba[0][1]


# 转换概率值为 float 类型
disease_probability = np.float64(prediction_proba[0][1])

formatted_probability = '{:.3f}'.format(disease_probability)

# 使用SHAP解释模型预测结果
explainer = shap.Explainer(xg)
shap_values = explainer.shap_values(X_test.astype(float))

# 计算输入参数对结果的贡献
contribution_values = np.abs(shap_values.mean(0))
contribution_values_normalized = contribution_values / np.sum(contribution_values)  # 归一化贡献度
sorted_indices = np.argsort(contribution_values_normalized)[::-1]  # 降序排列
top_contributions = sorted_indices[:8]  # 取前五个贡献最大的参数

# 构建贡献结果字典
contributions_dict = {}
for index in top_contributions:
    feature_name = df.columns[index]
    contribution =float(contribution_values_normalized[index])
    cont= '{:.3f}'.format(contribution)
    contributions_dict[feature_name] = cont




# 输出患病概率和参数贡献前五的值
result_dict = [
    { "probability": formatted_probability},
    {"contributions": contributions_dict}
]


# 将结果转换为 JSON 字符串并打印
result_json = json.dumps(result_dict, ensure_ascii=False)
print(result_json)

# 预测单例样本
# prediction = xg.predict(instance)
#
# # 输出预测结果
# if prediction == 0:
#     result="预测结果：无心脏病"
# else:
#     result="预测结果：有心脏病"
#
# result_dict = [result]
#
# # 将结果转换为JSON字符串并打印
# result_json = json.dumps(result_dict)
# print(result_json)
