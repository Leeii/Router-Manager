### ARouter 路由管理

组件化项目中使用ARouter，发现有几个问题
1. 各个模块的路由都是写死在模块中
2. 如果模块跟很多地方都有引用，就造成路径到处都是，无法实现统一管理
3. IProvider 多实现无法管理

所以就在想怎么才能将路由统一的管理的起来，并且要好用，明了
发现 `Retrofit2` 根据动态代理模式来实现的方式，就可以很好的实现统一接口管理

#### 使用方法



1. 使用的时候集成进项目，让中间层依赖，路由管理写到中间层，所有模块都能调用
2. 用法跟`Retrofit`用法差不多


##### 注解

1. `@Path` 路由对应的路劲
2. `@With` 路由携带的参数
3. `@Activity` 使用startActivity跳转需要的传入Activity
4. `@RequestCode` 上述步骤中需要的requestCode
5. `@Flags` 页面跳转时的Intent.flag
