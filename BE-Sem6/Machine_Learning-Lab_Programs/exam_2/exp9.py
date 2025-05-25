import pandas as pd 
import numpy as np 
import matplotlib.pyplot as plt 
from sklearn.datasets import fetch_olivetti_faces 
 
data=fetch_olivetti_faces() 
data.keys() 
 
print("Data shape",data.data.shape) 
print("Target Shapes",data.target.shape) 
print("There are {} unique persons in the dataset".format(len(np.unique(data.target)))) 
print("Size of each image is {}x{}".format(data.images.shape[-1],data.images.shape[1])) 
 
def print_faces(images,target,top_n): 
 top_n=min(top_n,len(images)) 
 grid_size=int(np.ceil(np.sqrt(top_n))) 
 fig,axes=plt.subplots(grid_size,grid_size,figsize=(15,15)) 
 fig.subplots_adjust(left=0,right=1,bottom=0,top=1,hspace=.2,wspace=.2) 
 
 for i ,ax in enumerate(axes.ravel()): 
     if i<top_n: 
         ax.imshow(images[i],cmap="bone") 
         ax.axis("off") 
         ax.text(2,12,str(target[i]),fontsize=9,color="red") 
         ax.text(2,55,f"face:{i}",fontsize=9,color="blue") 
     else: 
         ax.axis('off') 
 plt.show() 
print_faces(data.images,data.target,400) 
 
def display_unique_faces(pics): 
 fig=plt.figure(figsize=(24,10)) 
 col,row=10,4 
 
 for i in range(1,col*row+1): 
     img_index=10*i-1 
     if img_index<pics.shape[0]: 
         img=pics[img_index,:,:] 
         ax=fig.add_subplot(row,col,i) 
         ax.imshow(img,cmap="gray") 
         ax.set_title(f"Person {i}",fontsize=14) 
         ax.axis('off')
         
plt.suptitle("There are 40 distinct person faces are there in the dataset",fontsize=24) 
plt.show() 

display_unique_faces(data.images) 
from sklearn.model_selection import train_test_split 
x=data.data 
y=data.target 

xtrain,xtest,ytrain,ytest=train_test_split(x,y,test_size=.3,random_state=46) 
print("x train :",xtrain.shape) 
print("x test :",xtest.shape) 

from sklearn.naive_bayes import GaussianNB,MultinomialNB 
from sklearn.metrics import confusion_matrix,accuracy_score,classification_report 

nb=GaussianNB() 
nb.fit(xtrain,ytrain) 
ypred=nb.predict(xtest) 
nbaccuracy=round(accuracy_score(ytest,ypred)*100,2) 
cm=confusion_matrix(ytest,ypred) 

print("confusion matrix:",cm) 
print(f"Naive bayes accuracy:{nbaccuracy}") 

nb=MultinomialNB() 
nb.fit(xtrain,ytrain) 
ypred=nb.predict(xtest) 

accuray=round(accuracy_score(ytest,ypred)*100,2) 
print(f"Multinominal Naive Bayes acccuracy:{accuracy}") 
missclass=np.where(ypred!=ytest)[0] 
nummissclass=len(missclass)
 
print("number of missclassified images",nummissclass) 
print("Total images in test set",len(ytest)) 
print("Accuray:",round((1-nummissclass/len(ytest))*100,2),"%") 

nmissclass=min(nummissclass,5) 
plt.figure(figsize=(10,5)) 

for i in range(nmissclass): 
    idx=missclass[i] 
    plt.subplot(1,nmissclass,i+1) 
    plt.imshow(xtest[idx].reshape(64,64),cmap="gray") 
    plt.axis("off") 
    plt.show()