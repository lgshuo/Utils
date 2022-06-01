--LGSHttpUtils
val params = mapOf(
"username" to "1001",
"password" to "123456"
)
LGSHttpUtils.post(
"http://cloud.zhejiangfantian.com/index/huihu/ajaxGetAccountLine",
params = params,
block = {
it.show(
applicationContext
)
},
errorBlock = {
it.show(applicationContext) })
