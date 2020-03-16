#banner管理   banner维护  接口文档 
## 获取  banner的详细
> 请求方式：GET  
> 请求地址：[/api/psmp-portal/portal/banners/{banner的id}] 
返回 banner 成功数据格式
```
{
    "code": 0,
    "data": {
       bnid	:"ffdfd"
       title :	"fd3"
       plurl :	"fd3"
       tpgs :	"fdfd"
       tpdx :	"fdfd"
       line :	"fdf"
       state :	"fdg"
       zflid :	1
       num :	1
       time :	"2018-12-28T06:22:51.000+0000"
    }
}
```
## 获取  banner列表信息
> 请求方式：GET  
> 请求参数："zflid":"分类编号","time": "创建时间","title": "主题","currentPage":"页码","pageSize":"每页数据量"   
> 请求地址：[/api/psmp-portal/portal/banners]  
返回  banner  成功数据格式 
```
{
    "code": 0,
        "data": {
            [
               "0":{
                      bnid :"dsda"
                       title :"dsds7676"
               }
               "1":{
                   bnid :"ffdfd"
                   title :	"fd3"
                   plurl :	"fd3"
                   tpgs :	"fdfd"
                   tpdx :	"fdfd"
                   line :	"fdf"
                   state :	"fdg"
                   zflid :	1
                   num :	1
                   time :	"2018-12-28T06:22:51.000+0000"
                   }
           ]
        }
}
```
## 增加   banner信息  操作   
> 请求方式：POST   
> 请求参数：{"title": "主题",”zflid“:"子分类id","plurl": "图片路径","num": "排序","state":"状态","line":"上/下线“}
> 请求地址：[/api/psmp-portal/portal/banners]
返回  banner  成功数据格式
```
{
    "code": 200,
    "msg": "",
    "data": 1
}
```
## 修改   banner信息  操作   
> 请求方式：PUT   
> 请求参数：{”bnid“:"bannerID","title": "主题",”zflid“:"子分类id","plurl": "图片路径","num": "排序","state":"状态","line":"上/下线“}
> 请求地址：[/api/psmp-portal/portal/banners]
返回  banner  成功数据格式
```
{
    "code": 200,
    "msg": "",
    "data": 1
}
```
## 删除   banner信息  操作   
> 请求方式：DELETE   
> 请求地址：[/api/psmp-portal/portal/banners/{banner的id}]
返回  banner  成功数据格式
```
{
    "code": 200,
    "msg": "",
    "data": 1
}
```
## 上传
> 请求方式：POST  
> 请求参数：file:name标签名
> 请求地址：[/api/psmp-portal/portal/bannerUploads]  
返回  上传  成功数据格式 
```
{
    "code": 0,
        "data": {
            fileName :"/p1546585249681.doc"
            fileSize :"440832"
            fileType :"doc"
        }
}
``