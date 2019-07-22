'''
Created on 2019年1月5日

@author: Bright
'''
import requests
import re
import json
from bs4 import BeautifulSoup
import time
from ctypes.test.test_pickling import name
headers = {
'cookie': '_gcl_au=1.1.1156954862.1546610104; __BWfp=c1546610104822x78e3858a4; _ga=GA1.2.1961184748.1546610105; cto_lwid=9268edf7-152b-4dc7-b485-8f8184f83208; SPC_IA=-1; SPC_F=ORXvYYAQMMMQjql8i97TYgKVxcTvN06F; REC_T_ID=525f27b8-1028-11e9-b982-f8f21e2c1868; SPC_EC=-; SPC_T_ID="Rpfe2nigknSmMAoR0d2dlgAYNJhdSchjG38CgSRUj08kX7N5qkBoqN+B4RgR3KabSA58GOiBoTEJgAN/Q/XttvbE51PcdpefFHfaFW/dGeM="; SPC_U=-; SPC_T_IV="7viEe923uZZNF85SRRb6ig=="; SPC_SI=l6t2ztkpwu8z9tvgencdh4nb8vvy26mc; _fbp=fb.1.1548329956625.507852414; AMP_TOKEN=%24NOT_FOUND; _gid=GA1.2.161905472.1548329957; csrftoken=okgmlbVhTuHFktlFlexjUo3Rru8YNp8k',
'referer': 'https://shopee.tw/search?keyword=%E7%90%83%E8%A1%A3&page=0&skipShuffle=true&sortBy=ctime&usedItem=true',
'user-agent': 'Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36',
}

url='https://shopee.tw/api/v2/search_items/?by=ctime&conditions=used&keyword=%E7%90%83%E8%A1%A3&limit=50&newest=0&order=desc&page_type=search&skip_shuffle=1'

'''
#'https://www.ptt.cc/bbs/Beauty/index.html'
#'https://shopee.tw/search?keyword=球衣&page=0&skipShuffle=true&sortBy=ctime&usedItem=true'
#https://shopee.tw
#https://tw.carousell.com/search/products/?sort_by=time_created%2Cdescending&query=%E7%90%83%E8%A1%A3&cc_id=1111
#https://feebee.com.tw/s/?q=球衣
#金石堂  https://www.kingstone.com.tw/search/result.asp?c_name=%25E6%259D%2591%25E4%25B8%258A%25E6%2598%25A5%25E6%25A8%25B9&se_type=4&dis=img
#露天  https://rtapi.ruten.com.tw/api/prod/v2/index.php/prod?id=21904689239159,21904689189658,21904665745931,21904665738607,21904665715354,21904665583469,21904647647101,21904647527546,21904647484065,21904636817108,21903627855930,21903626832406,21903626821392,21903624505254,21903624459061,21903622977817,21903622822771,21903622766554,21903622693407,21903613527751,21903613423510,21903608777527,21903590444966,21903575756819,21903575586506,21903575542643,21903575508049,21903575474580,21902550579948,21902550570860,21902550150965,21902547890052,21902547812371,21902547655062,21902526858042,21902508735024,21902506246096,21902504881639,21902504881224,21902504880964,21902504880065,21902504879704,21902504879298,21902504878859,21902504878804,21902504878523,21901460825480,21901449583725,21901411541144,21852393201017,21852385146050,21852357322033,21852357244856,21851308929592,21851286479171,21851286439166,21851286280351,21851286236958,21851286211704,21851286188482,21851286161753,21851286100105,21851286010868,21851285878094,21851285825096,21851264901863,21851264891007,21851264875740,21851264852431,21851264838738,21851264808735,21851264793930,21851264779383,21851264614022,21851264541458,21851264523706,21851264508002,21851264331769,21851264312915,21851264291876&2580558&_callback=jsonpcb_Prod'
#####蝦皮Network中的網址https://shopee.tw/api/v2/search_items/?by=ctime&conditions=used&keyword=%E7%90%83%E8%A1%A3&limit=50&newest=0&order=desc&page_type=search&skip_shuffle=1'
#蝦皮jo malone Network中的網址https://shopee.tw/api/v2/search_items/?by=ctime&conditions=used&keyword=jo%20malone&limit=50&newest=0&order=desc&page_type=search&skip_shuffle=1
#蝦皮 https://shopee.tw/search?keyword=球衣&page=0&skipShuffle=true&sortBy=ctime&usedItem=true
#蝦皮https://shopee.tw/NBA球衣-正品球衣-電繡網眼版-76隊-3號-艾弗森球衣-AI球衣-Allen-Ezail-Iverson球衣-i.15102866.1871918675
#Lativ https://www.lativ.com.tw/MEN
#Lativ https://www.lativ.com.tw/Product/GetNewProductCategoryList?MainCategory=MEN&pageIndex=0&cacheID=26580
#https://tw.carousell.com/search/products/?query=%E7%90%83%E8%A1%A3
'''
res=requests.get(url,headers=headers)
#print(res)
print(res.status_code)
x=res.text
#print(x)
data = json.loads(x)
#print("data=",data)#這邊傾取出來的資料中商品名稱就是有殘缺的
#data1 = json.loads(data)
#print(data1)
itemlist = data["items"]
#print('item=',itemlist)
#print(type(item))
'''
資料形式
data,each_item_id :dict
itemlist :list
'''
#time.sleep(1000)
b=1
c=0
for each_item_id in itemlist :
    #print(each_item_id)
    #time.sleep(0.1)
    
    for x in each_item_id:
#        print (x) #X的資料形式是str
#        time.sleep(1)
#        print(each_item_id[x])#印出each_item_id中的所有細節
#        time.sleep(0.1)
        if x == "name" :#印出每個商品的名字
            print(each_item_id[x],"\n")
            time.sleep(1)
            
        
        elif x=="ctime":
            print("XXXXXXXXXXX")
            print(each_item_id[x],"\n")
            
        
    
#        for y in each_item_id[x]:
#            print(each_item_id[x])
#            print (y,':',each_item_id[x][y]) 
#    time.sleep(100)
    



'''
cars = {'A':{'speed':70,
        'color':2},
        'B':{'speed':60,
        'color':3}}
for x in cars:
    print (x)
    print(cars[x])
    for y in cars[x]:
        print(cars[x])
        print (y,':',cars[x][y])   
''' 
