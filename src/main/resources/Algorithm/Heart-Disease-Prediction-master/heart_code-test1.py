import pickle

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


df = pd.read_csv('F:\Code\Heart-Disease-Prediction-master\cleveland.csv', header = None)

df.columns = ['age', 'sex', 'cp', 'trestbps', 'chol',
              'fbs', 'restecg', 'thalach', 'exang', 
              'oldpeak', 'slope', 'ca', 'thal', 'target']

### 1 = male, 0 = female
df.isnull().sum()

df['target'] = df.target.map({0: 0, 1: 1, 2: 1, 3: 1, 4: 1})
df['sex'] = df.sex.map({0: 'female', 1: 'male'})
df['thal'] = df.thal.fillna(df.thal.mean())
df['ca'] = df.ca.fillna(df.ca.mean())

import matplotlib.pyplot as plt
import seaborn as sns

# distribution of target vs age 
# sns.set_context("paper", font_scale = 2, rc = {"font.size": 20,"axes.titlesize": 25,"axes.labelsize": 20})
# sns.catplot(kind = 'count', data = df, x = 'age', hue = 'target', order = df['age'].sort_values().unique())
# plt.title('Variation of Age for each target class')
# plt.show()
#
#
# # barplot of age vs sex with hue = target
# sns.catplot(kind = 'bar', data = df, y = 'age', x = 'sex', hue = 'target')
# plt.title('Distribution of age vs sex with the target class')
# plt.show()

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

#########################################   SVM   #############################################################
# from sklearn.svm import SVC
# classifier = SVC(kernel = 'rbf')
# classifier.fit(X_train, y_train)
#
# # Predicting the Test set results
# y_pred = classifier.predict(X_test)
#
# from sklearn.metrics import confusion_matrix
# cm_test = confusion_matrix(y_pred, y_test)
#
# y_pred_train = classifier.predict(X_train)
# cm_train = confusion_matrix(y_pred_train, y_train)
#
# print()
# print('Accuracy for training set for svm = {}'.format((cm_train[0][0] + cm_train[1][1])/len(y_train)))
# print('Accuracy for test set for svm = {}'.format((cm_test[0][0] + cm_test[1][1])/len(y_test)))


#########################################   Naive Bayes  #############################################################
# X = df.iloc[:, :-1].values
# y = df.iloc[:, -1].values
#
# from sklearn.model_selection import train_test_split
# X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.2, random_state = 0)
#
# from sklearn.naive_bayes import GaussianNB
# classifier = GaussianNB()
# classifier.fit(X_train, y_train)
#
#
# # Predicting the Test set results
# y_pred = classifier.predict(X_test)
#
# from sklearn.metrics import confusion_matrix
# cm_test = confusion_matrix(y_pred, y_test)
#
# y_pred_train = classifier.predict(X_train)
# cm_train = confusion_matrix(y_pred_train, y_train)
#
# print()
# print('Accuracy for training set for Naive Bayes = {}'.format((cm_train[0][0] + cm_train[1][1])/len(y_train)))
# print('Accuracy for test set for Naive Bayes = {}'.format((cm_test[0][0] + cm_test[1][1])/len(y_test)))


#########################################   Logistic Regression  #############################################################
# X = df.iloc[:, :-1].values
# y = df.iloc[:, -1].values
#
# from sklearn.model_selection import train_test_split
# X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.2, random_state = 0)
#
# from sklearn.linear_model import LogisticRegression
# classifier = LogisticRegression()
# classifier.fit(X_train, y_train)
#
# # Predicting the Test set results
# y_pred = classifier.predict(X_test)
#
# from sklearn.metrics import confusion_matrix
# cm_test = confusion_matrix(y_pred, y_test)
#
# y_pred_train = classifier.predict(X_train)
# cm_train = confusion_matrix(y_pred_train, y_train)
#
# print()
# print('Accuracy for training set for Logistic Regression = {}'.format((cm_train[0][0] + cm_train[1][1])/len(y_train)))
# print('Accuracy for test set for Logistic Regression = {}'.format((cm_test[0][0] + cm_test[1][1])/len(y_test)))

#########################################   Decision Tree  #############################################################
# X = df.iloc[:, :-1].values
# y = df.iloc[:, -1].values
#
# from sklearn.model_selection import train_test_split
# X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.2, random_state = 0)
#
# from sklearn.tree import DecisionTreeClassifier
# classifier = DecisionTreeClassifier()
# classifier.fit(X_train, y_train)
#
# # Predicting the Test set results
# y_pred = classifier.predict(X_test)
#
# from sklearn.metrics import confusion_matrix
# cm_test = confusion_matrix(y_pred, y_test)
#
# y_pred_train = classifier.predict(X_train)
# cm_train = confusion_matrix(y_pred_train, y_train)
#
# print()
# print('Accuracy for training set for Decision Tree = {}'.format((cm_train[0][0] + cm_train[1][1])/len(y_train)))
# print('Accuracy for test set for Decision Tree = {}'.format((cm_test[0][0] + cm_test[1][1])/len(y_test)))
#

#########################################  Random Forest  #############################################################
# X = df.iloc[:, :-1].values
# y = df.iloc[:, -1].values
#
# from sklearn.model_selection import train_test_split
# X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.2, random_state = 0)
#
# from sklearn.ensemble import RandomForestClassifier
# classifier = RandomForestClassifier(n_estimators = 10)
# classifier.fit(X_train, y_train)
#
# # Predicting the Test set results
# y_pred = classifier.predict(X_test)
#
# from sklearn.metrics import confusion_matrix
# cm_test = confusion_matrix(y_pred, y_test)
#
# y_pred_train = classifier.predict(X_train)
# cm_train = confusion_matrix(y_pred_train, y_train)
#
# print()
# print('Accuracy for training set for Random Forest = {}'.format((cm_train[0][0] + cm_train[1][1])/len(y_train)))
# print('Accuracy for test set for Random Forest = {}'.format((cm_test[0][0] + cm_test[1][1])/len(y_test)))

###############################################################################
# applying lightGBM
# import lightgbm as lgb
#
# d_train = lgb.Dataset(X_train, label = y_train)
# params = {}
#
# clf = lgb.train(params, d_train, 100)
# #Prediction
# y_pred = clf.predict(X_test)
# #convert into binary values
# for i in range(0, len(y_pred)):
#     if y_pred[i]>= 0.5:       # setting threshold to .5
#        y_pred[i]=1
#     else:
#        y_pred[i]=0
#
# from sklearn.metrics import confusion_matrix
# cm_test = confusion_matrix(y_pred, y_test)
#
# y_pred_train = clf.predict(X_train)
#
# for i in range(0, len(y_pred_train)):
#     if y_pred_train[i]>= 0.5:       # setting threshold to .5
#        y_pred_train[i]=1
#     else:
#        y_pred_train[i]=0
#
# cm_train = confusion_matrix(y_pred_train, y_train)
# print()
# print('Accuracy for training set for LightGBM = {}'.format((cm_train[0][0] + cm_train[1][1])/len(y_train)))
# print('Accuracy for test set for LightGBM = {}'.format((cm_test[0][0] + cm_test[1][1])/len(y_test)))


###############################################################################
# applying XGBoost

#from sklearn.model_selection import train_test_split
#X_train, X_test, y_train, y_test = train_test_split(X, target, test_size = 0.20, random_state = 0)

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
age = float(input("请输入年龄："))
sex = input("请输入性别（female/male）：")
cp = float(input("请输入胸痛类型："))
trestbps = float(input("请输入静息血压："))
chol = float(input("请输入胆固醇："))
fbs = float(input("请输入空腹血糖："))
restecg = float(input("请输入静息心电图结果："))
thalach = float(input("请输入最大心率："))
exang = float(input("请输入运动诱发心绞痛："))
oldpeak = float(input("请输入运动引起ST段的压低："))
slope = float(input("请输入ST段的斜率："))
ca = float(input("请输入主要血管数："))
thal = float(input("请输入缺陷的类型："))
# 将性别转换为数值表示
if sex == 'female':
    sex = 0
else:
    sex = 1
# 构建单例样本
instance = [[age, sex, cp, trestbps, chol, fbs, restecg, thalach, exang, oldpeak, slope, ca, thal]]
instance = sc.transform(instance)  # 特征缩放

# 预测单例样本
prediction = xg.predict(instance)

# 输出预测结果
if prediction == 0:
    print("预测结果：没有心脏病")
else:
    print("预测结果：有心脏病")