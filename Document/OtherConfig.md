## 其他配置
前面我们介绍了如果将SpringSecurity作为我们的登录校验框架 并且实现了三种方式的校验 但是光是这样
自由度还远远不够 在实际开发场景中 我们还会面对各种各样的需求 这一部分我们接着来进行更加深层次的配置

### 自定义登录界面
虽然SpringSecurity为我们提供了一个还行的登录界面 但是很多情况下往往都是我们使用自定义的登录界面 这个时候就需要进行更多的配置了 我们还是以之前图书管理系统使用的模版为例

下载好模版后 我们将其中的两个页面和资源文件放到类路径下:

<img src="https://image.itbaima.net/markdown/2023/07/03/hpZs1DLESojHJue.png"/>

接着我们配置对应页面的Controller控制器:

```java
                    @Controller
                    public class HelloController {
    
                        @GetMapping("/")
                        public String index(){
                            return "index";
                        }
                    
                        @GetMapping("/login")
                        public String login(){
                            return "login";
                        }
                        
                    }
```

这样 我们在登录之后 就可以展示前端模版页面了:

<img src="https://image.itbaima.net/markdown/2023/07/03/Zns4Vwb7zPLc6SQ.png"/>

不过现在依然是默认进入到SpringSecurity默认的登录界面 现在我们来配置自定义的登录界面 将我们的前端模版中的登录页面作为SpringSecurity的默认登录界面

```java
                    @Configuration
                    @EnableWebSecurity
                    public class SecurityConfiguration {
                    
                      	...
                      
                    	// 如果你学习过SpringSecurity5.X版本 可能会发现新版本的配置方式完全不一样
                        // 新版本全部采用lambda形式进行配置 无法再使用之前的and()方法进行连接了
                        @Bean
                        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                              
                            return http
                                    // 以下是验证请求拦截和放行配置
                                    .authorizeHttpRequests(auth -> {
                                        auth.anyRequest().authenticated(); // 将所有请求全部拦截 一律需要验证
                                    })
                                    // 以下是表单登录相关配置
                                    .formLogin(conf -> {
                                        conf.loginPage("/login"); // 将登录页设置为我们自己的登录页面
                                        conf.loginProcessingUrl("/doLogin"); // 登录表单提交的地址 可以自定义
                                        conf.defaultSuccessUrl("/"); // 登录成功后跳转的页面
                                        conf.permitAll(); // 将登录相关的地址放行 否则未登录的用户连登录界面都进不去
                                      	// 用户名和密码的表单字段名称 不过默认就是这个 可以不配置 除非有特殊需求
                                        conf.usernameParameter("username");
                                        conf.passwordParameter("password");
                                    })
                                    .build();
                            
                        }
                        
                    }
```

需要配置登陆页面的地址和登陆请求发送的地址 这里登陆页面填写为/login 登陆请求地址为/doLogin 登陆页面我们刚刚已经自己编写Controller来实现了
登陆请求提交处理由SpringSecurity提供 只需要写路径就可以了 现在访问我们的网站 就可以进入到自定义的登录界面了

<img src="https://image.itbaima.net/markdown/2023/07/03/c38kewdxtn1j2V6.png"/>

但是我们发现 我们的页面只有一个纯文本 这是因为在获取静态资源的时候 所有的静态资源默认情况下也会被拦截 因此全部被302重定向到登录页面 这显然是不对的:

<img src="https://image.itbaima.net/markdown/2023/07/03/6vXlPZprzjJLEeq.png"/>

因此 现在我们需要将所有的静态资源也给放行 否则登录界面都没法正常展示:

```java
                    .authorizeHttpRequests(auth -> {
                          auth.requestMatchers("/static/**").permitAll(); // 将所有的静态资源放行 一定要添加在全部请求拦截之前
                          auth.anyRequest().authenticated(); // 将所有请求全部拦截 一律需要验证
                    })
```

<img src="https://image.itbaima.net/markdown/2023/07/03/LmZbihzD4vYB5GF.png"/>

因此 如果各位小伙伴后续在编写项目过程中发现有302的情况 一定要先检查是否因为没有放行导致被SpringSecurity给拦截了 别再遇到302一脸懵逼了

接着我们来配置登录操作 这里我们只需要配置一下登录的地址和登录按钮即可 当然 跟之前一样 要把CSRF的输入框也加上

```html
                    <form action="doLogin" method="post">
                    		...
                      <input type="text" name="username" placeholder="Email Address" class="ad-input">
                      ...
                      <input type="password" name="password" placeholder="Password" class="ad-input">
                      ...
                      <input type="text" th:name="${_csrf.getParameterName()}" th:value="${_csrf.token}" hidden>
                      <div class="ad-auth-btn">
                         <button type="submit" class="ad-btn ad-login-member">Login</button>
                      </div>
                    	...
                    </form>
```

接着我们就可以尝试进行登录操作了:

<img src="https://image.itbaima.net/markdown/2023/07/03/P2LS8uNRQ64WEvT.png"/>

可以看到 现在我们可以成功地登录到主页了

退出登录也是同样的操作 我们只需要稍微进行一下配置就可以实现 我们首先继续完善配置类:

```java
                    @Configuration
                    @EnableWebSecurity
                    public class SecurityConfiguration {
                    
                        ...
                    
                        @Bean
                        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                            
                            return http
                                    ...
                                    // 以下是退出登录相关配置
                                    .logout(conf -> {
                                        conf.logoutUrl("/doLogout"); // 退出登录地址 跟上面一样可自定义
                                        conf.logoutSuccessUrl("/login"); // 退出登录成功后跳转的地址 这里设置为登录界面
                                        conf.permitAll();
                                    })
                                    .build();
                            
                        }
                        
                    }
```

接着我们来稍微魔改一下页面中的退出登录按钮:

```html
                    <li>
                       <form action="doLogout" method="post">
                            <input type="text" th:name="${_csrf.getParameterName()}" th:value="${_csrf.token}" hidden>
                            <button type="submit">
                               <i class="fas fa-sign-out-alt"></i> logout
                            </button>
                       </form>
                    </li>
```

现在我们点击右上角的退出按钮就可以退出了:

<img src="https://image.itbaima.net/markdown/2023/07/03/yM8TOAxYPf3iqFs.png"/>

不过 可能会有小伙伴觉得 我们现在无论提交什么请求都需要CSRF校验 有些太麻烦了 实际上现在浏览器已经很安全了 没必要防御到这种程度 我们也可以直接在配置中关闭CSRF校验:

```java
                    @Configuration
                    @EnableWebSecurity
                    public class SecurityConfiguration {
                    
                       	...
                          
                        @Bean
                        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                               
                            return http
                                    ...
                                    // 以下是csrf相关配置
                                    .csrf(conf -> {
                                        conf.disable(); // 此方法可以直接关闭全部的csrf校验 一步到位
                                        conf.ignoringRequestMatchers("/xxx/**"); // 此方法可以根据情况忽略某些地址的csrf校验
                                    })
                                    .build();
                            
                        }
                    }
```

这样 我们就不需要再往页面中嵌入CSRF相关的输入框了 发送请求时也不会进行校验 至此 我们就完成了简单的自定义登录界面配置

### 记住我功能











