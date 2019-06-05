# CCHESS
本组的项目为一个可供人们消遣娱乐、愉悦心情、消磨时间、促进友谊、提高智商的利用Java语言和配套GUI在PC端实现的中国象棋游戏，支持在Windows/MacOS/Linux等多种环境下运行。采用扁平化的界面设计风格，界面制作精良，拥有特效与动画。分为客户端和服务器端两个程序，服务端用于支持客户端之间人人对战进行数据交换，且一个服务端可以同时支持多个客户端连接，并独立对战；客户端连接到同一个服务端即可发起对战，并支持认输、输赢判断、悔棋、计时等基本功能。在孤寂无聊时，本软件也具备单机人机对战的功能，可以与电脑直接对战，且融入了智能算法。

目前待解决的问题与对应分工
-------

软件的icon，即标题前面小图标的制作（王沛然）

~~软件的logo，用于放在主界面显示（王沛然）

~~软件的棋子（王沛然）~~

移动棋子的过程动画（廖承相）
(目前部分解决移动问题，只能在自己的棋子走动时有残影)

人机对战、人人联机对战实现和对应界面衔接（侯伊为 赵倩锐）

移动棋子后的特效（吃东西）（廖承相 王海林 王沛然）

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
![4](https://github.com/HiramWHL/CCHESS/blob/master/show/4.png)
