# CCHESS
本组的项目为一个可供人们消遣娱乐、愉悦心情、消磨时间、促进友谊、提高智商的利用Java语言和配套GUI在PC端实现的中国象棋游戏，支持在Windows/MacOS/Linux等多种环境下运行。采用扁平化的界面设计风格，界面制作精良，拥有特效与动画。分为客户端和服务器端两个程序，服务端用于支持客户端之间人人对战进行数据交换，且一个服务端可以同时支持多个客户端连接，并独立对战；客户端连接到同一个服务端即可发起对战，并支持认输、输赢判断、（悔棋、计时）等基本功能。在孤寂无聊时，本软件也具备单机人机对战的功能，可以与电脑直接对战，且融入了智能算法。

需要注意的是，由于jar和exe依赖资源文件，需要复制到src目录下才可以运行，其中exe还必须将jdk目录复制到同一目录下并命名为jre才可运行

建议使用一键安装包，不依赖java环境：

链接：https://pan.baidu.com/s/1Y6svh_pfMy3NTPtou37lqQ 

提取码：qjwi 


目前待解决的问题与对应分工
-------

~~软件的icon，即标题前面小图标还有软件logo的制作（王沛然）（已由王海林代劳）~~

~~软件的logo，用于放在主界面显示（王沛然）~~

~~软件的棋子（王沛然）~~

~~移动棋子的过程动画（廖承相）
(已解决)~~

~~人机对战、人人联机对战实现和对应界面衔接（侯伊为 赵倩锐）~~

~~移动棋子后的特效（吃东西）（廖承相 王海林 王沛然）（放弃）~~

~~变量名优化（廖承相 王沛然）(放弃)~~

更新日志
-------

**BY WHL 6.8:**

**经过半天一夜的不懈努力，完成了划时代的大更新，如果没有严重的BUG，这将是我们项目的最终版本，不再更新**

1.彻底修复编码问题，将String单独独立为StringIndex.java，作为一个单独的类提供提示文本，所有文本数据通过其中的成员方法返回

2.将所有棋子的标识符从中文换成英文，修复由于标识符为中文的编码问题造成的规则 胜负判断出错的问题

3.引入Java Zoom包播放音乐，并启用多线程播放背景音乐，解决了音乐播放无法跨平台以及因为音乐播放阻塞线程导致的棋子移动有残影的问题，同时利用线程递归调用实现音乐循环播放

4.修改服务器与客户端交互逻辑，解决了因为重名连接服务器失败却提示成功的问题

5.修改客户端处理服务器返回数据的逻辑，对战端也可以显示棋子移动轨迹

6.每次玩家名称更改为随机生成，避免重复

7.修改软件icon和启动界面，完成优化

8.修改软件的媒体资源加载逻辑

9.将软件打包为jar包，并利用C语言调用jre生成exe，并打包对应安装程序，运行exe即可进行安装，无需java环境

目前软件有源代码（需编译运行） 安装包（双击安装运行） exe包（双击exe运行） jar包（双击jar运行 需要java运行环境）

鉴于Github上传下载速度慢，仅在GitHub上上传源代码和jar包

+++++++++++++++++++++++++++++++++

BY LCX 6.7:

1、修改了主程序切入窗口的实现，（从控制台循环输出--> sleep) 。
 调整主界面的居中和窗口按键大小。
 目前的主入口不变。

2、去掉了文件中各个地方的原来的署名，更新了包名(zwlhw)，去掉了com，少一层子文件。为了避免检查修改缺漏，希望大家进行测试，看看是否有问题。

3、添加了人机部分的各个必要的地方主要的中文注释。
  为了减小人人，人机的风格差异，在添加每个大类前，添加了部分英文说明(水平有限，不够准确，请大家帮忙修正。)
  由于serve部分代码我没有涉足，所以没有添加注释。
  
4、上传了博弈论论文。

5、删除了部分无用的冗余函数。
  
problem：

1、在奋战了一天之后，我已失败！依旧没有解决怎么在人机的走棋位置添加标记和音效。希望另外有人能解决这个问题。
2、关于代码风格问题我还是失败！，由于重组了代码，导致现在整个结构很混乱，我无法将风格调整，准备放弃调整。
3、该死的编码问题依旧又发生了，于是我无法测试人人对战是否会因为我的改包而发生错误，希望大家多多尝试，看看有无bug。
4、并且希望大家想个办法彻底解决编码问题。

综上所述，我还想的就是：测试有无bug+添加走动标记+添加人机的音效+还可以在进入的主界面添加一个背景图。
或许我们的最终测试版，估计就是这样了。


+++++++++++++++++++++++++++++++++

BY WHL 6.5:

经过数小时的不懈努力，人人部分和人机部分的GUI衔接已经顺利实现

注意目前程序主入口已经切换为了GameStart.java

+++++++++++++++++++++++++++++++++

BY ZQR & HYW, 6.3:

后端已完成，开始时选择1/2分别进入人人/人机对战模式。

人机部分运用了象棋博弈论思想实现机器的最优解回棋。

+++++++++++++++++++++++++++++++++

BY ZQR & HYW, 6.1（2）：

源码雏形已出，但存在几个问题：

类名、变量名等命名捉急，不规范。

出现了一个bug，人人联机对战模式的服务器端和客户端的GUI窗口关不掉了。

+++++++++++++++++++++++++++++++++

BY ZQR & HYW，6.1(1):

经测试，联机对战这部分，“将”和“帅”被吃掉后会提示本局结束，按确认键后自动回到初始“邀请/接受挑战状态”。

联机对战的匹配方法是，在1号机上开服务器端，并在客户端输入IP地址和端口号，记为player1; 2号机上开客户端即可，输入1号机的IP地址和相同端口号，记为player2, 并确认连接。此时1号机的服务器端会出现player2的IP，表示已加入。

红黑方判断与棋盘翻转待解决。

+++++++++++++++++++++++++++++++++

BY LCX：

目前存在一个问题

从测试来看，在将“将”和“帅”吃掉之后居然还能继续下棋，说明代码出现了bug，目前推测是因为编码问题，无法判断错误导致的。

移动残影目前只能在自己客户端看到自己的棋子移动有残影。

修改了窗口使之能够适应进行居中。

添加了bgm，但是又注释掉了，函数还在，需要的时候再考进去音乐就好啦。

添加了棋子单纯移动时撞击木板棋盘 的音效。

添加了吃掉对方棋子时的“吃”的音效。

目前还想处理的问题是 使棋盘反转过来，通过判断红方于黑方，使之能契合习惯。

+++++++++++++++++++++++++++++++++



相关截图
-------


![1](https://github.com/HiramWHL/CCHESS/blob/master/show/1.png)
![2](https://github.com/HiramWHL/CCHESS/blob/master/show/2.png)
![3](https://github.com/HiramWHL/CCHESS/blob/master/show/3.png)
![4](https://github.com/HiramWHL/CCHESS/blob/master/show/5.png)
![5](https://github.com/HiramWHL/CCHESS/blob/master/show/4.png)
