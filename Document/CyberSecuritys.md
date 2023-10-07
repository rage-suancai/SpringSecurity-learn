## 网络安全基础
网络安全是当今互联网时代非常重要的一个议题 在信息爆炸的今天 网络安全问题频频发生 给个人和组织带来了严重的损失和威胁 随着技术的发展和互联网的普及
我们越来越意识到网络安全已经成为一个全球性的挑战 本部分 我们会给各位小伙伴介绍网络安全的相关概念 以及常见的Web服务器攻击形式 如果你已经了解相关内容 可以直接跳过被模块

其中比较典型的案例有以下几个:

<img src="https://image.itbaima.net/markdown/2023/07/01/W3oCIbqvLUn492J.png"/>

在2017年 一款名叫WannaCry的勒索软件席卷全球 导致全球大量计算机受到影响 得益于全球网络的发达 这款病毒从小规模很快发展至全球范围 勒索病毒是自熊猫烧香以来影响力最大的病毒之一

    WannaCry (又叫Wanna Decryptor) 一种"蠕虫式"的勒索病毒软件 大小3.3MB 由不法分子利用NSA(National Security Agency, 美国国家安全局)泄露的危险漏洞"EternalBlue"
    (永恒之蓝)进行传播 勒索病毒肆虐 俨然是一场全球性互联网灾难 给广大电脑用户造成了巨大损失 最新统计数据显示 100多个国家和地区超过10万台电脑遭到了勒索病毒攻击,感染
    勒索病毒是自熊猫烧香以来影响力最大的病毒之一 WannaCry勒索病毒全球大爆发 至少150个国家,30万名用户中招 造成损失达80亿美元 已经影响到金融 能源 医疗等众多行业 造成严重的危机管理问题

<img src="https://image.itbaima.net/markdown/2023/07/01/wBdynQcKU2g7LjN.png"/>

除了病毒在网络上传播外 还有非常恶心的DDoS攻击 这种攻击方式成本低 效率高 只要持续一段时间就能导致没有特殊防御的网站无法正常运作:

    2022年4月 郑州警方接到报警称 某政府网站平台于近日遭受多次DDoS攻击 导致平台访问量瞬间暴增多倍 造成数十家政府网站和政务新媒体账号无法正常访问
    接到报警后 郑州警方立即开展侦查调查 第一时间锁定犯罪嫌疑人张某某 仅22小时就将犯罪嫌疑人张某某抓捕到案

    经审查 张某某为某医院网络安全部技术维护人员 2022年4月2日 其个人网站被DDoS攻击 导致网站无法登陆 为减少攻击对其个人网站的影响
    张某某私自将个人网站域名解析地址变更为某政府网站平台地址 “转嫁”攻击流量 致使该政府网站平台被恶意网络流量攻击 多家政府网站,政务新媒体账号不能正常使用

    目前 张某某因涉嫌破坏计算机信息系统犯罪被公安机关移送起诉 案件正在进一步侦办中

<img src="https://image.itbaima.net/markdown/2023/07/01/5aHJDMTiLOSzERn.png"/>

信息泄露也是非常严重的网络安全问题 我们的个人信息是我们的隐私 而在网络发达的今天 在各大APP上填写的信息很有可能会被第三方获取 从中谋取利益

    今年早些时候便已发现相关安全漏洞的网络安全专家称 一个常见漏洞导致了这场有记录以来最大规模的个人数据泄露 也是中国所遭遇最大规模的网络安全事件 造成数据被公开在互联网上 供人取用

    据这些网络安全专家称 上海警方的这些记录是被安全存储的 其中包含个人姓名,身份证号码,电话号码以及警情信息 涉及近10亿中国公民的数据 他们说
    但一个用于管理和访问该数据库的显示界面(dashboard)被设置在一个公开网址上 没有加设密码 任何有相对基本技术知识的人都可以轻松访问,复制或窃取库中的海量信息

    暗网情报公司Shadowbyte的创始人Vinny Troia说: “他们把这么多数据暴露在外 这太疯狂了” 这家公司专门在网上扫描搜寻存在安全漏洞的数据库 早在今年1月扫描时发现了上海警方的这个数据库

个人信息数据一旦泄露 相当于别人可以通过网络直接定位你的住址,电话号码,各种社交账号,身份证信息甚至是机票,火车票 酒店开房信息等
做的再绝一定 如果这个酒店被人偷偷放了针孔摄像头 甚至还能根据记录查到你开房视频 这些事情光是想想都可怕 这等同于在互联网上"裸奔"

网络安全问题非同小可 而我们作为网站的开发者 更应该首当其冲解决这些潜在问题 接下来 我们会介绍几个常见的Web网站攻击方式 以及在后续使用SpringSecurity时如何去防范这些攻击行为

### 测试环境搭建
为了测试我们之前的网站安全性 这里我们基于MVC框架重新搭建一个采用之前的验证方式的简易网站 首先是登录界面部分:

```html
                    <!DOCTYPE html>
                    <html lang="en">

                    <head>
                        <meta charset="UTF-8">
                        <title>登录白马银行</title>
                    </head>

                    <body>
                      <form action="login" method="post">
                        <label>
                          用户名:
                          <input name="username" type="text">
                        </label>
                        <label>
                          密码:
                          <input name="password" type="password">
                        </label>
                        <button type="submit">登录</button>
                      </form>
                      <div th:if="${status}">登录失败 用户名或密码错误</div>
                    </body>

                    </html>
```

接着是登录之后的首页

```html
                    <!DOCTYPE html>
                    <html lang="en">

                    <head>
                        <meta charset="UTF-8">
                        <title>白马银行 - 首页</title>
                    </head>
                    
                    <body>
                    
                    </body>

                    </html>
```

接着是Controller部分:

```java
                    @Controller
                    public class HelloController {
    
                        // 处理登录操作并跳转
                        @PostMapping("/login")
                        public String login(@RequestParam String username,
                                            @RequestParam String password,
                                            HttpSession session,
                                            Model model){
                            
                            if("test".equals(username) && "123456".equals(password)) {
                                session.setAttribute("login", true);
                                return "redirect:/";
                            } else {
                                model.addAttribute("status", true);
                                return "login";
                            }
                            
                        }
                    
                        // 处理首页或是登录界面跳转
                        @GetMapping("/")
                        public String index(HttpSession session){
                            
                            if(session.getAttribute("login") != null) {
                                return "index";
                            }else {
                                return "login";
                            }
                            
                        }
                        
                    }
```

这样我们就可以进行简单登录了:

<img src="https://image.itbaima.net/markdown/2023/07/01/7jW5Nzki8urUhaf.png"/>

接着我们在首页加一个转账操作 要求填写转账人账号名称:

```java
                    @ResponseBody
                    @PostMapping("/pay")
                    public JSONObject pay(@RequestParam String account, HttpSession session) {
                
                        JSONObject object = new JSONObject();
                
                        if (session.getAttribute("login") != null) {
                            System.out.println("转账给: " + account + "成功 交易已完成");
                            object.put("success", true);
                        } else {
                            System.out.println("转账给: " + account + "失败 用户未登录");
                            object.put("success", false);
                        }
                        return object;
                
                    }
```

接着我们在页面中添加一个简单的转账操作按键:

```html
                    <!DOCTYPE html>
                    <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                        <title>白马银行 - 首页</title>
                        <script src="https://unpkg.com/axios@1.1.2/dist/axios.min.js"></script>
                    </head>
                    <body>
                        <div>
                            <label>
                                转账账号：
                                <input type="text" id="account"/>
                            </label>
                            <button onclick="pay()">立即转账</button>
                        </div>
                    </body>
                    </html>
                    
                    <script>
                    function pay() {
                        const account = document.getElementById("account").value
                        axios.post('/mvc/pay', { account: account }, {
                            headers: {
                                'Content-Type': 'application/x-www-form-urlencoded'
                            }
                        }).then(({data}) => {
                            if(data.success)
                                alert("转账成功")
                            else
                                alert("转账失败")
                        })
                    }
                    </script>
```

这样我们就成功搭建好网络安全的测试项目了 各位小伙伴请将这个项目进行保存 后面需要重复使用

<img src="https://image.itbaima.net/markdown/2023/07/01/khHj6YqSJimGKec.png"/>