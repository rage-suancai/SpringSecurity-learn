## 授权
用户登录后 可能会根据用户当前是什么身份进行角色划分 比如我们最常用的QQ 一个QQ群里面 有群主,管理员和普通群成员三种角色 其中群主具有最高权限
群主可以管理整个群的任何板块 并且具有解散和升级群的资格 而管理员只有群主的一部分权限 只能用于日常管理 普通群成员则只能进行最基本的聊天操作

<img src="https://image.itbaima.net/markdown/2023/07/04/e1IXMRgawYoGvSQ.png"/>

对于我们来说 用户的一个操作实际上就是在访问我们提供的接口(编写的对应访问路径的Servlet)比如登陆 就需要调用/login接口
退出登陆就要调用/logout接口 而我们之前的图书管理系统中 新增图书,删除图书 所有的操作都有着对应的Servlet来进行处理
因此 从我们开发者的角度来说 决定用户能否使用某个功能 只需要决定用户是否能够访问对应的Servlet即可

我们可以大致像下面这样进行划分:
- 群主: /login, /logout, /chat, /edit, /delete, /upgrade
- 管理员: /login, /logout, /chat, /edit
- 普通群成员: /login, /logout, /chat

也就是说 我们需要做的就是指定哪些请求可以由哪些用户发起

SpringSecurity为我们提供了两种授权方式:
- 基于权限的授权: 只要拥有某权限的用户 就可以访问某个路径
- 基于角色的授权: 根据用户属于哪个角色来决定是否可以访问某个路径

两者只是概念上的不同 实际上使用起来效果差不多 这里我们就先演示以角色方式来进行授权

### 基于角色授权
现在我们希望创建两个角色 普通用户和管理员 普通用户只能访问index页面 而管理员可以访问任何页面

首先我们需要对数据库中的角色表进行一些修改 添加一个用户角色字段 并创建一个新的用户 Test用户的角色为user 而Admin用户的角色为admin

接着我们需要配置SpringSecurity 决定哪些角色可以访问哪些页面:

```java
                    .authorizeHttpRequests(auth -> {
                        
                        // 静态资源依然全部可以访问
                        auth.requestMatchers("/static/**").permitAll();
                        // 只有具有以下角色的用户才能访问路径"/"
                        auth.requestMatchers("/").hasAnyRole("user", "admin");
                        // 其他所有路径必须角色为admin才能访问
                        auth.anyRequest().hasRole("admin");
                        
                    })
```

接着我们需要稍微修改一下验证逻辑 我们在数据库中的用户表上添加一个新的字段 用于表示角色:

<img src="https://image.itbaima.net/markdown/2023/07/04/1pkfGS9LrsPtjFx.png"/>

修改一下对应的实体类:

```java
                    @Data
                    public class Account {
                    
                        int id;
                        String username;
                        String password;
                        String role;
                        
                    }
```

现在我们在查询用户时 需要添加其对应的角色:









