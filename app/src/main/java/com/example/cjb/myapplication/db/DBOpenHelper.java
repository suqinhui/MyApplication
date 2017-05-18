package com.example.cjb.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "kbook.db";// 数据库名称
    private static final int DATABASE_VERSION = 1;// 数据库版本

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override//当初次创建数据库的时候调用，一般把建库、建表的操作
    public void onCreate(SQLiteDatabase db) {
        //书籍表
        db.execSQL("create table book_table(book_name varchar(20) PRIMARY KEY not null,book_english_name varchar(20) not null,author varchar(20) not null,introduction text not null,years varchar(20) not null,image varchar(255) not null)");
        //章节表
        db.execSQL("create table book_chapter(book_english_name varchar(20) not null,chapter_name varchar(20) not null,chapter_id varchar(20) not null,content text not null)");
        //问题表
        db.execSQL("create table question(book_english_name varchar(20) not null,chapter_id varchar(20) not null,question_content text not null,answer_a varchar(100) not null,answer_b varchar(100) not null,answer_c varchar(100) not null,answer_d varchar(100) not null,right_answer varchar(100) not null)");
        //用户是否回答过该章节问题表
        db.execSQL("create table answer_table(username varchar(20) not null,book_english_name varchar(20) not null,chapter_id varchar(20) not null)");
        //用户表
        db.execSQL("create table account(username varchar(20) PRIMARY KEY not null,password varchar(20) not null,nickname varchar(20) not null,sex varchar(20) not null,age varchar(20) not null,email varchar(20) not null,all_integration varchar(20) not null,today_integration varchar(20) not null,timing varchar(20) not null,face_image varchar(255) not null)");
        //用户收藏书籍表
        db.execSQL("create table user_collect(username varchar(20) not null,book_name varchar(20)not null,book_english_name varchar(20) not null)");
        //用户笔记表，id自增
        db.execSQL("create table user_note(id INTEGER PRIMARY KEY autoincrement,username varchar(20) not null,book_english_name varchar(20) not null,note_title varchar(20) not null,note_content varchar(20) not null)");
        //用户错题集
        


        //插入一个管理员用户
        db.execSQL("insert into account(username,password,nickname,sex,age,email,all_integration,today_integration,timing,face_image)values('admin','admin','管理员','请选择','','','0','0','0','')");

        //插入书籍表
        db.execSQL("insert into book_table(book_name,book_english_name,author,introduction,years,image)values('疯狂java实战演义','crazy_java','杨恩雄 麦凯翔','《 疯狂Java实战演义》以15个生动的Java案例，引领读者体验Java开发的乐趣。书中使用Java的Swing技术开发了若干个游戏，从这些游戏中可以了解到，Java一样可以做出优秀的游戏和应用程序。本书知识点丰富，适合有一定Java基础、有意向做Java桌面应用程序或者想了解Java图形界面编程的读者阅读，也可作为Java开发程序员的案例参考书。','2010-6-02','dfafadfadfafd')");
        db.execSQL("insert into book_table(book_name,book_english_name,author,introduction,years,image)values('android网络开发','book1','苏钦辉','这里是简介这里是简介','1883','fadfafa')");
        db.execSQL("insert into book_table(book_name,book_english_name,author,introduction,years,image)values('android开发入门与实践体验','book2','宋一博','这里是简介这里是简介','1997','fadfafa')");
        db.execSQL("insert into book_table(book_name,book_english_name,author,introduction,years,image)values('android开发入门与实战','book3','钱仁法','这里是简介这里是简介','1994','fadfafa')");

        //插入书籍章节
        //android开发
        db.execSQL("insert into book_chapter(book_english_name,chapter_name,chapter_id,content)values('book1','第1章','1','这里是android开发第一章内容')");
        db.execSQL("insert into book_chapter(book_english_name,chapter_name,chapter_id,content)values('book1','第2章','2','这里是android开发第二章内容')");
        db.execSQL("insert into book_chapter(book_english_name,chapter_name,chapter_id,content)values('book1','第3章','3','这里是android开发第三章内容')");
        db.execSQL("insert into book_chapter(book_english_name,chapter_name,chapter_id,content)values('book1','第4章','4','这里是android开发第四章内容')");

        db.execSQL("insert into book_chapter(book_english_name,chapter_name,chapter_id,content)values('book2','第1章','1','这里是android开发第一章内容')");
        db.execSQL("insert into book_chapter(book_english_name,chapter_name,chapter_id,content)values('book2','第2章','2','这里是android开发第二章内容')");
        db.execSQL("insert into book_chapter(book_english_name,chapter_name,chapter_id,content)values('book2','第3章','3','这里是android开发第三章内容')");
        db.execSQL("insert into book_chapter(book_english_name,chapter_name,chapter_id,content)values('book2','第4章','4','这里是android开发第四章内容')");

        db.execSQL("insert into book_chapter(book_english_name,chapter_name,chapter_id,content)values('book3','第1章','1','这里是android开发第一章内容')");
        db.execSQL("insert into book_chapter(book_english_name,chapter_name,chapter_id,content)values('book3','第2章','2','这里是android开发第二章内容')");
        db.execSQL("insert into book_chapter(book_english_name,chapter_name,chapter_id,content)values('book3','第3章','3','这里是android开发第三章内容')");
        db.execSQL("insert into book_chapter(book_english_name,chapter_name,chapter_id,content)values('book3','第4章','4','这里是android开发第四章内容')");

        // 疯狂java
        db.execSQL("insert into book_chapter(book_english_name,chapter_name,chapter_id,content)values('crazy_java','第1章 控制台五子棋','1','1.1 引言\n" +
                "控制台五子棋，顾名思义，就是在Java控制台运行的五子棋游戏，需要用户用键盘输入棋子的位置来进行游戏。\n" +
                "由于是在控制台下面运行的程序，所以并没有漂亮的游戏界面，与及鼠标操作等东西，只是在一片黑色控制台环境下进行游戏，游戏的可玩性并不高，似乎这并不是一个完整的游戏。虽然如此，但事实上，一个程序最重要的并不是界面，而是处理各种业务逻辑与数据的方法，只要掌握了核心的方法，掌握基础的知识，便更容易学习awt,swing等图形用户界面的编写，万变不离其宗，写起有操作界面的程序也会变得更加容易，更加随心应手。而本章的主要目的让读者掌握与理解Java编程的基础知识，因此，掌握本章五子棋的实现原理，对于学习以后的章节将会非常有帮助。作为本书的第一章内容，我们在本章中将使用最简单的方式来实现一个控制台五子棋游戏。\n" +
                "1.1.1 五子棋介绍\n" +
                "五子棋是起源于中国古代的传统黑白棋种之一。现代五子棋日文称之为“连珠” ，英译为”Renju”，英文称之为”Gobang”或”FIR”（Five in a Row 的缩写 ） ，亦有“连五子”、“五子连” 、“串珠”、“五目”、 “五目碰”、“五格”等多种称谓。五子棋游戏是一个比较大众的棋类游戏，大多数人都会玩这个游戏，五子棋的玩法与规则如下：\n" +
                "\uF071五子棋是两个人之间进行的竞技活动，由于对黑方白方规则不同，黑棋必须先行（本章节设计的游戏，黑棋与白棋的规则一样，但一样由黑棋先下）。\n" +
                "\uF071五子棋专用盘为 15×15 ，五连子的方向为横、竖、斜。\n" +
                "\uF071在棋盘上以对局双方均不可能形成五连为和棋。\n" +
                "\uF071首先形成五连子的一方为赢。\n" +
                "五子棋必须由双方进行游戏，当某一方按照一定规则连成五个棋子的时候，该游戏方就胜利，在本章中，我们并不需要做到对战形式的，我们可以设计一个简单的“电脑”来做我们的对手，当我们下完棋后，这个简单的“电脑”就随便在棋盘中下一个棋，当然，如果想做更强大的“电脑”我们可以编写程序来实现，当我们下棋的时候，这个“电脑”就对我们所下的棋子进行检测，并将棋子下到最恰当的位置。本章主要目的是展现五子棋的实现原理，如果读者有兴趣，可以自行开发强大的“人工智能电脑”来进行游戏。\n" +
                "1.1.2 输入输出约定\n" +
                "玩家必须以（x,y）的方式输入棋盘的坐标，其中，x代表棋坐标，y代表竖坐标。x与y的值必须是1到N（棋盘的大小）的正数。\n" +
                "系统询问玩家是否继续游戏时，玩家输入y是代表继续，输入其它则代表退出游戏。\n" +
                "“●”代表黑子，“○”代表白子。当玩家以（x,y）的形式输入下棋的坐标后，游戏中就可以根据玩家所下的坐标，再去将棋子放置到棋盘中。我们可以将棋盘看作一个二维数组，填充着棋盘式的标志（“十”），玩家下棋后，将棋子替换原来的标志，最后再执行输入。由于本章是在控制台中进行打印，因此只需要使用System.out.println来进行打印即可，如果需要实现有界面的五子棋游戏，例如使用swing或者awt，可以使用相应的方法，将二维数组“画”到界面中。因此，不管是使用swing、awt或者其他界面技术，五子棋的实现原理几乎大同小异。\n" +
                "1.2 了解游戏流程描述\n" +
                "在开发五子棋之前，我们先了解一下游戏的整个游戏流程，了解游戏的流程，有助于我们在开发的过程中可以清晰的掌握程序结构，对于实现功能有莫大的帮助，五子棋的具体流程如图1.1所示。\n" +
                "\n" +
                "图 1.1 五子棋游戏流程\n" +
                "1.2.1 玩家输入坐标\n" +
                "游戏开始，系统在控制台中打印出棋盘，玩家根据这个棋盘，选定下棋的位置坐标后，在控制台中输入相应的坐标，系统读取玩家所输入的坐标并进行相应的分析，如果玩家所下的棋使得玩家游戏胜利，则系统询问是否继续游戏。\n" +
                "系统读取了玩家输入的坐标后，除了判断游戏是否胜利外，还需要判断玩家输入的坐标中是否已经存在了相应的棋子，如果存在的话，需要再次提示玩家，重新输入。\n" +
                "1.2.2 “电脑”下棋\n" +
                "玩家输入了坐标，系统判断玩家没有游戏胜利后，就应该轮到“电脑”下棋，在本章的开头中我们已经讲到，本章可以实现一个简单的电脑来进行游戏，只需要随便的产生棋盘坐标，就可以让“电脑”在相应的坐标中下棋。如果电脑随机产生的坐标中已经存在棋子，那么我们可以重新随机产生坐标，直到产生的坐标上没有存在棋子为止。当“电脑”下完棋后，就可以使用同样的判断方式（判断是否五子相连）来判断“电脑”所下的棋子是否已经使得游戏胜利，如果游戏胜利，同样地去提示玩家，电脑已经胜利了。\n" +
                "在本章我们并不需要实现强大的人工智能“电脑”，只需要简单的随机产生坐标即可。\n" +
                "1.3 创建游戏的各个对象\n" +
                "这里设计三个类来完成游戏的功能，棋盘类（Chessboard），游戏类（GobangGame）与棋子类（Chessman）（枚举类），类的关系如图1.2所示，从图中可以看出，Chessboard依赖于GobangGame，gobangGame的改变，会影响到Chessboard状态的改变，而Chessman与GobangGame是一个聚合关系。下面一一介绍。\n" +
                "\n" +
                "图1.2 五子棋类图\n" +
                "1.3.1 Chessboard类\n" +
                "要进行五子棋游戏，必须有有一个棋盘，而这个类主要控制棋盘的初始化，输出与及增加新的棋子。这个类包含以下方法：\n" +
                "\uF071void initBoard()，这个方法用于初始化棋盘，开始新的游戏时，应该调用此方法，初始化出一个新的空棋盘。\n" +
                "\uF071void printBoard()，此方法用于在控制台输出棋盘，各方每一完一颗棋子后，由于棋盘上棋子的状态有改变，所以必须调用此方法重新输入棋盘。\n" +
                "\uF071void setBoard( int posX , int posY , String chessman )，posX与posY是新下棋子的x与y坐标,，chessman是新下棋子的类型（黑子与白子），每下完一颗棋子后，通过调用此方法把棋子设置到棋盘上。\n" +
                "\uF071String[][] getBoard()，返回棋盘，返回类型是保存棋盘的二维数组。\n" +
                "当我们需要初始化棋盘的时候，可以直接调用Chessboard的initBoard方法，我们需要考虑该方法需要实现的功能：初始化棋盘。由于我们将棋盘看作是一个二维数组，因此initBoard就需要帮我们去创建一个二维数组，创建二维数组可以使用以下代码。\n" +
                "代码清单：code\\gobang\\src\\org\\crazyit\\gobang\\Chessboard.java\n" +
                "\t\tObject[][] array = new Object[size][size];\n" +
                "\t\tfor (int i = 0; i < array.length; i++) {\n" +
                "\t\t\tfor (int j = 0; j < array[i].length; j++) {\n" +
                "\t\t\t\tarray[i][j] = new Object();\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "以上代码创建一个固定大小（一维与二维大小）的二维数组，再通过嵌套循环为数组中的每一个元素进行赋值。在游戏中如果我们进行了下棋的操作，可以直接改变这个数组的某一个元素值。在创建Chessboard类时，我们就需要发挥面向对象的思维，在我们的程序中，所有看到的或者想的事物，我们都可以将其抽象成具体的某个对象，并赋予一定的属性与行为。在设计对象的过程中，如果有某些事物拿捏不准，不知如何设计属性或者行为，可以将其设计成接口或者抽象类。\n" +
                "Chessboard中提供了一个printBoard的方法用于打印棋盘，在本章中，我们就需要将棋盘数组打印到控制台中，因此该方法可以简单的调用System.out.print去打印相关的字符串。需要注意的是，由于printBoard方法是没有参数的，因此我们需要为Chessboard提供一个二维数组变量，当调用printBoard方法的时候，将对象内的二维数组打印，我们可以将Chessboard看作一个有状态的Java对象，有状态的Java对象可以理解成一个Java对象保存一些与该对象相关的状态属性，如果该对象没有保存与该对象相关的状态属性，那么我们可以将这个对象看成一个无状态的Java对象。\n" +
                "当外部调用Chessboard的setBoard方法时，就可以将某个值设置到Chessboard中的二维数组里，告诉Chessboard玩家或者“电脑”在某个位置下了怎样的棋子。\n" +
                "1.3.2 Chessman类\n" +
                "Chessman类是一个枚举类，此类是构造器私有的，不能直接创建，里面有BLACK与WHITE两个静态属性，代表黑子与白子枚举类，两个表态属性都是Chessman类型的，要获取棋子，则通过这两个属性调用以下的方法获取棋子：\n" +
                "\uF071String getChessman()，返回String类型的棋子实例，“●”或者“○”。\n" +
                "如果我们需要得到棋子的字符串（“●”或者“○”），可以使用以下的代码。\n" +
                "代码清单：code\\gobang\\src\\org\\crazyit\\gobang\\Chessman.java\n" +
                "Chessman.BLACK.getChessman();\n" +
                "1.3.3 GobangGame类\n" +
                "GobangGame类是进行游戏的类，Chessboard依赖于此类，此类控制游戏的开始，重玩与结束，并影响Chessboard类。主要包含以下构造器与方法：\n" +
                "\uF071GobangGame()，默认无参数构造器。\n" +
                "\uF071GobangGame( Chessboard chessboard )，有参数构造器，以一个Chessboard实例去初始化这个类。\n" +
                "\uF071boolean isValid( String inputStr )，此方法验证控制台的输入字符串是否合法，如果合法，返回true，如果不合法，则返回false，此方法抛出Exception异常。\n" +
                "\uF071void start()，开始游戏。此方法抛出Exception异常。\n" +
                "\uF071boolean isReplay( String chessman )，是否重新开始游戏，如果是，返回true，否则返回false，参数chessman代表黑子或白子。\n" +
                "\uF071int[] computerDo()，计算机随机下棋，由计算机自动设置棋盘，并返回包含新下棋子位置x与y坐标的int[]数组类型。\n" +
                "\uF071boolean isWon( int posX , int posY , String ico )，判断输赢，参数posX与posY代表新下棋子的x与y坐标，ico代表新下的棋子类型，如果赢了，返回true，否则返回false。\n" +
                "GobangGame是我们五子棋游戏的主体类，游戏里面所有的处理都在该类中实现。GobangGame中的isValid方法用于验证控制台的输入，玩家主要在控制台输入下棋的坐标，下棋的坐标的字符串形式为：x,y，我们需要对字符串进行处理得到x和y的值，如果玩家输入的字符串不符合系统要求，则isValid方法返回false，只有当该方法返回true的时候，才会去修改Chessboard的二维数组。\n" +
                "GobangGame中提供了一个start方法，用于游戏的开始，我们需要考虑游戏开始的行为，例如需要初始化棋盘（调用Chessboard的init方法），需要开始从控制台读取玩家的输入信息、打印棋盘，验证控制台输入的信息等，这些功能我们将在下面的章节中加以描述。\n" +
                "当轮到“电脑”下棋的时候，我们需要随机生成电脑的下棋坐标，GobangGame中的computerDo方法用于随机产生坐标。\n" +
                "判断一局游戏是否胜利，可以调用GobangGame的isWon方法，该方法判断游戏是否胜利，是五子棋中最主要的方法，五子棋是否可以相连的所有逻辑，都会在该方法中实现。isWon方法会在每次下棋后（玩家下棋或者“电脑”下棋）调用。\n" +
                "到此，游戏中的三个对象已经设计完成，这三个对象中已经定义好了各种方法，并在前面章节中详细描述了各个方法的作用，在下面章节中我们将开始对这三个对象所定义的方法进行实现。当然，如果需要做到更好的程序解耦，我们可以使用一些设计模式，例如将游戏规则写成一个具体的算法，可以使用策略模式，如果需要产生出不同的棋子（将控制台换成其他界面），可以编写棋子工厂等。但是本章主要目的是展现一个最简单的五子棋，因此本章中并不涉及任何具体的设计模式。\n" +
                "1.4 棋盘类实现\n" +
                "在此类中，主要是用一个String[][]类型的二维数组board去保存棋盘，board [i][j]代表棋盘的某个位置(i代表x坐标，j代表y坐标)，如果此位置没有棋子，默认值为“十”，如果有棋子，board [i][j]的值为“●”或者“○”。用一个不可改变的常量BOARD_SIZE来表示棋盘的大小，所以保存这个棋盘的是一个BOARD_SIZE*BOARD_SIZE的二维数组。图1.3描述了为什么需要使用一个二维数组来代表一个棋盘，如果把棋盘的一列当做一个数组，那么N列的棋盘就是一个二维数组，用数组能很好的存储与表现这个棋盘。\n" +
                "\n" +
                "图1.3 棋盘与数组的关系\n" +
                "1.4.1 初始化棋盘\n" +
                "在1.3节介绍过，此类主要是实现棋盘初始始化、输出、与更新，在这节便用代码一步一步地实现各个功能。首先我们需要初始化棋盘的实现，看以下代码片段。\n" +
                "代码清单：code\\gobang\\src\\org\\crazyit\\gobang\\Chessboard.java\n" +
                "public void initBoard() {\n" +
                "\t//初始化棋盘数组\n" +
                "\tboard = new String[BOARD_SIZE][BOARD_SIZE];\n" +
                "\t//把每个元素赋值为“十”，用于控制台输出棋盘\n" +
                "\tfor( int i = 0 ; i < BOARD_SIZE ; i++ ) {\n" +
                "\t\tfor( int j = 0 ; j < BOARD_SIZE ; j++ ) {\n" +
                "\t\t\tboard[i][j] = \"十\";\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n" +
                "上面代码中，BOARD_SIZE是代表棋盘的大小，用一个String[][]类型的二维数组来代表棋盘，创建此数组后，通过迭代为为个数组元素的值赋为“十”来初始化棋盘。创建了棋盘数组后，如果需要定位到棋盘的某个位置，只需要得到棋盘数组的一维值与二维值即可，例如处理玩家下棋动作的时候，可以将数组中具体的某个“十”替换成具体的棋子字符串。\n" +
                "1.4.2 输出棋盘\n" +
                "输出棋盘，只需要Chessboard的board属性（二维数组）的每一个值，打印到控制台中。如果可以做到更好的扩展性，我们可以在二维数组中存放棋子对象，而不是简单的字符串，那么存放在二维数组中的每一个棋子对象，都可以实现某个棋子接口或者继承棋子的抽象类，这样可以更好的做到游戏的扩展性。当然，我们在本章为了简单起见，只在该二维数组中存放需要打印的字符串，打印时只需要得到具体的某个二维数组的元素，将其打印即可。\n" +
                "代码清单：code\\gobang\\src\\org\\crazyit\\gobang\\Chessboard.java\n" +
                "public void printBoard() {\n" +
                "//打印每个数组元素\n" +
                "for( int i = 0 ; i < BOARD_SIZE ; i++ ) {\n" +
                "\t\tfor( int j = 0 ; j < BOARD_SIZE ; j++ ) {\n" +
                "\t\t\t//打印后不换行\n" +
                "\t\t\tSystem.out.print( board[i][j] );\n" +
                "\t\t}\n" +
                "\t\t//每打印完一行数组元素就换行一次\n" +
                "\t\tSystem.out.print(\"\\n\");\n" +
                "}\n" +
                "}\n" +
                "棋盘的输出与棋盘的初始化相类似，都是要遍历保存棋盘的数组，只不过是每遍历到一个元素都要输出来，注意到这里的输出方法用的是System.out.print()而不是常用的System.out.println()，这里因为System.out.println()方法是输出后自动换行的，如果使用此方法，便达不到我们需要的效果，棋盘的输出效果如图1.4。\n" +
                "\n" +
                "图1.4 控制台五子棋的棋盘\n" +
                "打印出来的效果，就好像在控制台中出现了一个棋盘。\n" +
                "1.4.3 获取棋盘\n" +
                "在Chessboard中提供了一个getBoard的方法，用于返回本对象的棋盘二维数组，该方法一般在游戏类GobangGame中调用，游戏类得到棋盘的二维数组，可以用于判断棋盘中的某一个位置是否有棋子或者计算游戏是否胜利。\n" +
                "getBoard方法只需要将本对象中的board（二维数组）返回即可，代码如下。\n" +
                "代码清单：code\\gobang\\src\\org\\crazyit\\gobang\\Chessboard.java\n" +
                "/**\n" +
                "* 返回棋盘\n" +
                "* @return 返回棋盘\n" +
                "*/\n" +
                "public String[][] getBoard() {\n" +
                "\treturn this.board;\n" +
                "}\n" +
                "到此，棋盘类的几个方法都已经实现，该类的主要功能是创建棋盘、打印棋盘等，实现的过程中涉及了一些Java语言的基本操作，例如嵌套循环、创建二维数组等。在下面的小节中，我们将去实现游戏的核心部分。\n" +
                "1.5 棋子枚举类实现\n" +
                "在某些情况下，一个类的属性是有限而且固定的（在某些情况下），例如本章中的棋子类，它只有两个对象，黑棋和白棋。这种实例有限而且固定的类，在Java里面称为枚举类，枚举类的关健字用enum而不是class，此类中有两个枚举属性BLACK和WHITE，代表黑子与白子，代码实现如下：\n" +
                "代码清单：code\\gobang\\src\\org\\crazyit\\gobang\\Chessman.java\n" +
                "public enum Chessman {\n" +
                "\tBLACK(\"●\"),WHITE(\"○\");\n" +
                "\tprivate String chessman;\n" +
                "\t/**\n" +
                "\t * 私有构造器\n" +
                "\t */\n" +
                "\tprivate Chessman(String chessman) {\n" +
                "\t\tthis.chessman = chessman;\n" +
                "\t}\n" +
                "\t/**\n" +
                "\t * @return 黑棋或者白棋\n" +
                "\t */\n" +
                "\tpublic String getChessman() {\n" +
                "\t\treturn this.chessman;\n" +
                "\t}\n" +
                "}\n" +
                "在上面的代码中，可以看到，枚举类是用enum关键字代替了class关键字，看到此枚举类的构造器的权限修饰符是private，也是表明此类是不可以通过外部创建的，只能在此类的内部创建，这是为了保证此对象只有黑子与白子两种类型。黑体代码是列出枚举值，实际上就是调用私用构造器创建此对象，等同以下代码：\n" +
                "public static final Chessman BLACK = new Chessman(“●”);\n" +
                "public static final Chessman WHITE = new Chessman(“○”);\n" +
                "由于BLACK与WHITE两个属性是静态的，所以要获取黑子或者白子，可以通过以下代码来获得：\n" +
                "Chessman.BLACK.getChessman();\n" +
                "Chessman.WHITE.getChessman();\n" +
                "在控制台中，我们可以使用这种方式来确定棋子的字符串，如果我们需要在swing或者其他界面中展示一个棋子，可能需要为具体的某个棋子保存相应的棋子图片，在本章中，由于棋子只是普通的两个字符串，因此可以直接写成枚举对象即可。\n" +
                "如果你希望你的程能有更好的扩展性，笔者建议可以根据情况建立棋子接口，并提供白棋与黑棋的实现类，我们在棋盘二维数组中存放的只是某个接口，而不是具体的类，这样，提高了程序的可扩展性，在本小节的开头提到：在某些情况下，一个类中的属性有限并且是固定的。但是在我们开发的实际情况中（特别是做企业应用），随着业务的不断变化，类的不可变几乎是不可能的。举个例子，如果需要将本章中的五子棋迁移到swing界面中，那么该棋子枚举类就不得不更改了。\n" +
                "虽然本章是为了做一个较为简单的五子棋，但更多的想向大家展现面向对象的思维。\n" +
                "1.6 游戏类实现\n" +
                "本章中的游戏类是GogangGame，在该类中，主要控制游戏的开始，重新开始与结束，验证玩家输入的合法性，判断游戏的输与赢，调用棋盘类来初始化棋盘，打印棋盘，使用棋子类去设置棋盘等。此类中有四个属性，两个int类型的posX与poxY，用来存储玩家现在输入的x与y坐标（x和y坐标是指玩家输入的数字对应棋盘数组中的一维值与二维值），一个默认值为5的int类型常量WIN_COUNT，游戏胜利需要连接的棋子达到的连子数目，由于是五子棋，因此只需要5个棋子相连，游戏就胜利。还有一个Chessboard类型的变量chessboard，用来表示棋盘，游戏中就只用到一个棋盘，该对象可以使用初始化棋盘、打印棋盘、获得棋盘（数组）等方法。\n" +
                "1.6.1 使用BufferedReader获取键盘输入\n" +
                "BufferedReader是Java IO流中的一个字符包装流，它必须建立在字符流的基础之上。该对象可以从输入流中读取文本，但标准输入：System.in是字节流，所以程序需要使用转换流InputStreamReader将其包装成字符流。所以程序中用于获取键盘的输入采用以下代码创建。\n" +
                "//获取键盘的输入\n" +
                "BufferedReader br = new BufferedReader(new InputStreamReader(System.in));\n" +
                "String inputStr = null;\n" +
                "//br.readLine:每当键盘输入一行内容按回车键，则输入的内容被br读取到\n" +
                "while( (inputStr = br.readLine()) != null ) {\n" +
                "\t/**\n" +
                "      * 处理键盘输入\n" +
                "      */\n" +
                "}\n" +
                "BufferedReader中有一个readLine()方法，此方法总是读取下一行的输入字符串，如果没有下一行，则返回null。当得到玩家输入的字符后，我们可以进这些字符进行验证，验证完后，如果字符串符合系统要求，可以在验证处使用continue跳出本次循环。\n" +
                "如果需要读取输入，我们就需要为这些输入作出不同的判断，例如，玩家输入了y（继续游戏），那么我们就需要判断玩家输入了y后程序所需要执行哪些操作，因此，这样会为while循环体中增加许多的if语句，这些if语句会影响程序的可读性，如果需要将这些if语句去掉，我们可以将每个if中的代码抽取出来，作为具体的一个处理类。这样做不仅减少while循环体中的代码，而且可以使得程序更加清晰，程序的耦合度更低，while循环体中只负责读取玩家输入的字符串，而具体的处理则不必由该方法来执行。由于本章中的代码与动作相对较少，因此并不涉及如何实现以上所说的处理模式，更深入的可以查看“仿QQ游戏大厅”一章。\n" +
                "1.6.2 验证玩家输入字符串的合法性\n" +
                "根据引言中提到的输入约定，玩家在控制台输入的字符串必须是以(x,y)的方式输入，还需要验证输入的字符串是否能转换为数字，是否超越棋盘的边界（小于等于1，大于等于棋盘数组的长度），并且需要判断该位置是否已经存在棋子，具体判断流程如图1.5所示。\n" +
                "\n" +
                "图1.5 验证流程\n" +
                "首先，x与y必须是一个数字，由以下代码验证。\n" +
                "代码清单：code\\gobang\\src\\org\\crazyit\\gobang\\GobangGame.java\n" +
                "//将用户输入的字符串以逗号(,)作为分隔，分隔成两个字符串\n" +
                "String[] posStrArr = inputStr.split(\",\");\n" +
                "try {\n" +
                "\tposX = Integer.parseInt( posStrArr[0] ) - 1;\n" +
                "\tposY = Integer.parseInt( posStrArr[1] ) - 1;\t\t\t\t\n" +
                "} catch (NumberFormatException e) {\n" +
                "\tchessboard.printBoard();\n" +
                "\tSystem.out.println(\"请以(数字,数字)的格式输入：\");\n" +
                "\treturn false;\n" +
                "}\n" +
                "当我们调用Integer.parseInt方法将字符串转换成一个Integer类型的时候，如果需要转换的字符串不能转换成某一个数字，该方法将会抛出NumberFormatException异常，我们可以使用catch将该异常捕获，提示玩家需要重新输入棋子坐标。除了判断玩家输入的字符串是否符合我们游戏所要求的格式外，还需要判断玩家输入的坐标范围，即该范围不可小于1并不可大于棋盘数组的最大值，例如棋盘是10乘10，但玩家输入了11，那么将会抛出ArrayIndexOutOfBoundsException异常，因此，x与y的范围只能是大于1与小于N（棋盘的大小），如果超出这个范围，则需要提示玩家重新输入，由以下代码验证。\n" +
                "代码清单：code\\gobang\\src\\org\\crazyit\\gobang\\GobangGame.java\n" +
                "//检查输入数值是否在范围之内\n" +
                "if( posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0 \n" +
                "\t|| posY >= Chessboard.BOARD_SIZE ) {\n" +
                "\tchessboard.printBoard();\n" +
                "\tSystem.out.println( \"X与Y坐标只能大于等于1,与小于等于\" \n" +
                "\t\t+ Chessboard.BOARD_SIZE + \",请重新输入：\" );\n" +
                "\treturn false;\n" +
                "}\n" +
                "验证了输入坐标的合法性后，还需要验证玩家输入的坐标中是否已经存在棋子，我们通过Chessboard对象中的getBoard方法可以得到棋盘的二维数组，根据玩家输入的坐标得到数组中的元素，再判断元素是否已经是一个棋子（“●”或者“○”），如果该坐标中已经有棋子（元素值为“十”），则提示玩家重新输入，由以下代码验证。\n" +
                "代码清单：code\\gobang\\src\\org\\crazyit\\gobang\\GobangGame.java\n" +
                "//检查输入的位置是否已经有棋子\n" +
                "String[][] board = chessboard.getBoard();\n" +
                "if( board[posX][posY] != \"十\" ) {\n" +
                "\tchessboard.printBoard();\n" +
                "\tSystem.out.println( \"此位置已经有棋子，请重新输入：\" );\n" +
                "\treturn false;\n" +
                "}\n" +
                "以上代码中，如果board[i][j]不等于“十”(“十”是棋盘每个位置的默认值)，则证明此位置有棋子，需要提示玩家重新输入。这里需要注意的是，如果没有前一个判断（判断输入的坐标是否超过棋盘范围），那么通过棋盘数组获取某个元素时，就会抛出ArrayIndexOutOfBoundsException异常。\n" +
                "1.6.3 判断输赢\n" +
                "判断游戏输赢，需要在玩家输入了坐标并通过了合法性验证后（输入的坐标），再执行输赢的验证，同样地，如果是“电脑”随机生成的坐标，我们同样的需要进行输赢验证，因此，我们已经将判断输赢的行为，独立成一个isWon方法（详细请看1.3.3中的GobangGame类）。\n" +
                "判断输赢在本章的程序中稍微复杂，有两种方法来判断输赢：\n" +
                "\uF071每次下完一颗棋子，就通过程序从横、竖、斜各个方向扫描棋盘，如果在某个方向中，有同种颜色的棋子达到五连子，则此颜色的玩家为赢。如果没有相同颜色的棋子达到五连子，则继续游戏。该判断方法需要遍历整个棋盘，也就是意味着每次下棋后（玩家或者“电脑”）都需要对棋盘进行遍历，这样对程序的性能会造成一定的影响。\n" +
                "\uF071每次下完一颗棋子，以该棋子为中心，扫描在此棋子所在范围内的横、竖、斜方向，验证加上此棋子有没有形成五连子，如果形成五连子，则下棋子的玩家为赢。此方法与前面的方法比较，因为不需要扫描整个棋盘，所以更加快速，本章程序使用的是此方法，该方法的原理如图1.6所示。\n" +
                "\n" +
                "图1.6 五连子\n" +
                "在图1.6中可以看出，（0，0），（0，3），（0，6），（3，0），（6，0），（3，7），（7，3），（7，7）这些坐标都是此黑棋能形成五连子的最小或者最大位置的棋子，如果各个方向有足够的空间，就延伸到第五颗棋子，如果没有，就只延伸到边界。所以，只要能计算出任意一颗棋子的这些位置，我们就可以判断游戏的输赢，并且是以该棋子为中心向周围进行遍历。以下是判断输赢的代码实现。\n" +
                "代码清单：code\\gobang\\src\\org\\crazyit\\gobang\\GobangGame.java\n" +
                "//直线起点的X坐标\n" +
                "int startX = 0;\n" +
                "//直线起点Y坐标\n" +
                "int startY = 0;\n" +
                "//直线结束X坐标\n" +
                "int endX = Chessboard.BOARD_SIZE - 1;\n" +
                "//直线结束Y坐标\n" +
                "int endY = endX;\n" +
                "//同条直线上相邻棋子累积数\n" +
                "int sameCount = 0;\n" +
                "int temp = 0;\t\t\n" +
                "//计算起点的最小X坐标与Y坐标\n" +
                "temp = posX - WIN_COUNT + 1;\n" +
                "startX = temp < 0 ? 0 : temp;\n" +
                "temp = posY - WIN_COUNT + 1;\n" +
                "startY = temp < 0 ? 0 : temp;\n" +
                "//计算终点的最大X坐标与Y坐标\n" +
                "temp = posX + WIN_COUNT - 1;\n" +
                "endX = temp > Chessboard.BOARD_SIZE - 1 ? \n" +
                "\tChessboard.BOARD_SIZE - 1 : temp;\n" +
                "temp = posY + WIN_COUNT - 1;\n" +
                "endY = temp > Chessboard.BOARD_SIZE - 1 ? \n" +
                "\tChessboard.BOARD_SIZE - 1 : temp;\n" +
                "//从左到右方向计算相同相邻棋子的数目\n" +
                "String[][] board = chessboard.getBoard();\n" +
                "for( int i = startY; i < endY; i++) {\n" +
                "\tif( board[posX][i] == ico && board[posX][i+1] == ico ) {\n" +
                "\t\tsameCount++;\n" +
                "\t} else if( sameCount != WIN_COUNT - 1 ) {\n" +
                "\t\tsameCount = 0;\n" +
                "\t}\n" +
                "}\n" +
                "从上面代码中可以看到，首先是计算出在这颗棋子的直线上(横、竖、斜方向)能达到五连子的最小x、y坐标与最大x、y坐标，然后从最小x、y坐标访问到最大x、y坐标，如果此颜色棋子的相连累积数目达到五连子，则为赢。以上代码只是实现横向遍历判断，竖向遍历与斜向遍历的判断方法与横向遍历的实现基本类似。这里需要注意的是，当遇到一个可以相边的棋子，就需要为sameCount值加1。\n" +
                "1.6.4 计算机随机下棋\n" +
                "我们在前面章节中说到，使用一个简单的方式来产生一个“电脑”与我们进行对战。我们只需要使得到随便的坐标值，并且在该坐标中进行下棋操作，就可以实现“电脑”的下棋，因此，实现这个随机下棋的功能，最主要是产生随机的坐标。我们可以使用Math.random方法来产生0.0到1.0之间的double数组，再使用该值来乘以棋盘的大小，即可产生随机的坐标。\n" +
                "我们使用这个方式来产生随机坐标，因为是随机生成的位置，所以这个计算机是比较“笨”的。如果想让计算机变“聪明”起来，可以加上人工智能“电脑”，该人工智能“电脑”需要分析玩家的所有下棋的位置，并对这些位置的坐标进行相应的分析。以下是随机生成坐标的代码实现。\n" +
                "代码清单：code\\gobang\\src\\org\\crazyit\\gobang\\GobangGame.java\n" +
                "//随机生成x坐标，即二维数组具体一维的值\n" +
                "int posX = (int)(Math.random() * ( Chessboard.BOARD_SIZE - 1 ) );\n" +
                "//随机生成y坐标，即二维数组具体二维的值\n" +
                "int posY = (int)(Math.random() * ( Chessboard.BOARD_SIZE - 1 ) );\n" +
                "String[][] board = chessboard.getBoard();\n" +
                "//当棋盘中的位置不是“十”的时候（已经有棋子），则再次生成新的坐标\n" +
                "while( board[posX][posY] != \"十\" ) {\n" +
                "\tposX = (int)(Math.random() * ( Chessboard.BOARD_SIZE - 1 ) );\n" +
                "\tposY = (int)(Math.random() * ( Chessboard.BOARD_SIZE - 1 ));\t\t\n" +
                "}\n" +
                "这里需要注意的是，由于我们使用了while循环，其中循环条件是判断棋盘数组中是否已经存在棋秀，如果已经存在棋子，则需要重新随机生成坐标。那么就会产生这样一种情况，如果整个棋盘中都存在棋子的话，这个while将永远不会跳出，即死循环，所以我们需要判断棋盘中是否所有的位置都有棋子，如果棋盘中已经都存在棋子并且没有输赢的话，就可以提示玩家和棋了，重新开始游戏。\n" +
                "上面代码中，随机生成x与y坐标的过程是先用Math.random()方法获取一个在BOARD_SIZE(棋盘大小)范围之内的x与y正数坐标，如果这个坐标中已经有棋子，则继续使用Math.radom()方法获取坐标，直到这个坐标中没有棋子。\n" +
                "1.6.5 是否重新游戏\n" +
                "实现是否重新开始游戏的功能，这在这方法中，程序的流程是：如果玩家或者电脑赢了，则在控制台输出询问玩家是否重新开始游戏的信息，如果玩家输入”y”字符串，则重新开始游戏，否则直接退出整个程序，实现代码如下。\n" +
                "代码清单：code\\gobang\\src\\org\\crazyit\\gobang\\GobangGame.java\n" +
                "/**\n" +
                "* 是否重新开始下棋。\n" +
                " * @param chessman \"●\"为用户，\"○\"为计算机。\n" +
                " * @return 开始返回true，反则返回false。\n" +
                " */\n" +
                "public boolean isReplay( String chessman ) \n" +
                "\tthrows Exception {\n" +
                "\tchessboard.printBoard();\n" +
                "\tString message = chessman.equals(Chessman.BLACK.getChessman())\n" +
                " ? \"恭喜您，您赢了，\" : \"很遗憾，您输了，\";\n" +
                "\tSystem.out.println( message + \"再下一局？(y/n)\" );\n" +
                "\tBufferedReader br = new BufferedReader(new InputStreamReader(System.in));\n" +
                "\tif( br.readLine().equals(\"y\") ) {\n" +
                "\t\t//开始新一局\n" +
                "\t\treturn true;\n" +
                "\t}\n" +
                "\treturn false;\t\t\n" +
                "}\n" +
                "1.6.6 游戏过程实现\n" +
                "以下是游戏的流程说明，具体也可以看图1.2：\n" +
                "\uF071（1）首先调用Chessboard类型的chessboard属性中的initBoard()与printBoard()方法去初始化与打印棋盘。\n" +
                "\uF071（2）从控制台获取用户的输入。\n" +
                "\uF071（3）再调用本类的isValid()方法去验证玩家输入的合法性，如果输入不合法，返回第2步继续，否则到第4步。\n" +
                "\uF071（4）把玩家下的棋子位置赋值为\"●\"。\n" +
                "\uF071（5）调用isWon( int posX , int posY , String chessman )判断玩家是否赢了。如果玩家赢了，则调用isReply()方法输出的信息询问玩家是否重新游戏，如果玩家输入y，则返回第1步重新开始。\n" +
                "\uF071（6）调用computerDo()方法随机生成计算机的x,y坐标，并把board[x][y] 赋值为\"○\"。如果计算机赢了，则调用isReply()方法输出的信息询问玩家是否重新游戏，如果玩家输入y，则返回第1步重新开始，否则返回第2步轮到用户输入。\n" +
                "以下的代码实现以上的流程。\n" +
                "代码清单：code\\gobang\\src\\org\\crazyit\\gobang\\GobangGame.java\n" +
                "//true为游戏结束\n" +
                "boolean isOver = false;\n" +
                "chessboard.initBoard();\n" +
                "chessboard.printBoard();\n" +
                "//获取键盘的输入\n" +
                "BufferedReader br = new BufferedReader(new InputStreamReader(System.in));\n" +
                "String inputStr = null;\n" +
                "//br.readLine:每当键盘输入一行内容按回车键，则输入的内容被br读取到\n" +
                "while( (inputStr = br.readLine()) != null ) {\n" +
                "\tisOver = false;\n" +
                "\tif( !isValid( inputStr ) ) {\n" +
                "\t\t//如果不合法，要求重新输入，再继续\n" +
                "\t\tcontinue;\n" +
                "\t}\n" +
                "\t//把对应的数组元素赋为\"●\"\n" +
                "\tString chessman = Chessman.BLACK.getChessman();\n" +
                "\tchessboard.setBoard( posX , posY , chessman );\n" +
                "\t//判断用户是否赢了\n" +
                "\tif( isWon( posX , posY , chessman ) ) {\n" +
                "\t\tisOver = true;\n" +
                "\t} else {   \n" +
                "\t\t//计算机随机选择位置坐标\n" +
                "\t\tint[] computerPosArr = computerDo();\n" +
                "\t\tchessman = Chessman.WHITE.getChessman();\n" +
                "\t\tchessboard.setBoard( computerPosArr[0] , computerPosArr[1] , chessman );\n" +
                "\t\t//判断计算机是否赢了\n" +
                "\t\tif( isWon( computerPosArr[0] , computerPosArr[1] , chessman ) ) {\n" +
                "\t\t\tisOver = true;\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\t//如果产生胜者，询问用户是否继续游戏\n" +
                "\tif( isOver ) {\n" +
                "\t\t//如果继续，重新初始化棋盘，继续游戏\n" +
                "\t\tif( isReplay( chessman ) ) {\n" +
                "\t\t\tchessboard.initBoard();\n" +
                "\t\t\tchessboard.printBoard();\n" +
                "\t\t\tcontinue;\n" +
                "\t\t}\n" +
                "\t\t//如果不继续，退出程序\n" +
                "\t\tbreak;\n" +
                "\t}\n" +
                "\tchessboard.printBoard();\n" +
                "\tSystem.out.println(\"请输入您下棋的坐标，应以x,y的格式输入：\");\n" +
                "}\n" +
                "以上的代码中，我们使用了一个isOver来标识游戏是否胜利，当游戏胜利时，就询问玩家是否继续，我们可以看到，以上的代码中我们写了多个if语句，其实我们可以使用一些Java的基础知识来解决这些if问题（可以使用设计模式中的策略模式），当然大家也可能觉得这些if没有什么关系，但是，由于这些if的存在，会使得我们程序的可读性变差，在“仿QQ游戏大厅”章节，同样出现了读取字符串关作相应判断的情况，我们在该章节使用了其他方式去解决这些if语句，详细可以看“仿QQ游戏大厅”一章。本章的目的主要介绍一个简单五子棋的实现。\n" +
                "\t1.7 本章小结\n" +
                "    本章主要是介绍开发控制台五子棋的整个过程，体现流程设计与类设计的基本方法，示范了数组的使用，获取用户键盘的输入。使用了分支结构与循环结构的流程控制，还介绍与使用了枚举类。向读者灌输了面向对象的一些基本知识，通过这些基础的知识设计与开发出有趣味性的小游戏，让读者加深对这些基础知识的理解。')");
        db.execSQL("insert into book_chapter(book_english_name,chapter_name,chapter_id,content)values('crazy_java','第2章 仿Windows计算器','2','2.1 仿Windows计算器概述\n" +
                "Windows计算器，是Windows操作系统自带计算器,，可以帮助用户完成数据的运算，它可分为“标准型”和“科学型”，本章的仿Windows计算器是标准型的Java实现，标准型Windows计算器实现的主要功能有：四则运算；求倒数；求开方；存储计算结果；读取计算结果；累积计算结果。 \n" +
                "我们在第一章中，我们实现了一个在控制台进行的五子棋游戏，我们从本章开始将在Swing界面中实现本书的项目。在本章中，我们将使用到JFrame和JPanel两个Swing容器，使用到JTextField和JButton两个Swing容器，使用BorderLayout和GridLayout做两个布局器，以及使用到事件、事件监听器和事件适配器等。\n" +
                "实现一个计算器，界面中需要提供各种输入的按钮，再以这些按钮组成计算器的键盘，用户点击键盘输入值后，就可以将其所输入的值显示到一个文本框中，运算后，再将结果显示到文本框中。计算器的最终效果如图2.1所示。\n" +
                "\n" +
                "图 2.1 用Swing制作的计算器\n" +
                "从图2.1中可以看到，我们开发界面的时候，需要提供一个文本框在窗口的最上部，文本框下面再提供各个计算器的按钮。\n" +
                "2.1.1 数学与其它符号介绍\n" +
                "在此计算器中，主要使用的数学运算有加、减、乘、除四则运算，或者对一个正数进行开方，或者对一个非0的数学求倒数，使用到的数学符号有：\n" +
                "\uF071加、减、乘、除，对应使用的符号是“+”、“-”、“*”、“/”。\n" +
                "\uF071开方与倒数，对应使用的符号是“sqrt”和“1/x”。\n" +
                "\uF071求结果使用的数学符号是“=”。\n" +
                "\uF071“%”号，如果使用此符号，第二个操作数就等于两数相乘再除以100。\n" +
                "除了用于数学运算的符号，Windows计算器还提供对计算结果做存储、读取、累加、清除等操作，亦有对数字显示框中的数字做退格操作，还可以清除上次计算结果或者全部结果：\n" +
                "\uF071使用符号“MC”、“MR”、“MS”、“M+”代表清除存储结果、读取存储结果、保存存储结果和累加存储结果。\n" +
                "\uF071使用“Backspace”符号代表退格。\n" +
                "\uF071使用“CE”和“C”代表清除上次计算结果和清除所有计算结果。\n" +
                "四则运算在程序中可以直接使用Java运算符实现，实现开方可以调用Math类的sqrt方法，倒数可以使用1来除以原始的数字。当用户需点击“=”的时候，计算器就需要将最终的计算结果显示到文本框中。其他的计算器功能都可以通过计算器内部的程序实现，例如使用某个字符串或者数字来保存相应的结果，如果需要计取、存储、累加或者清除结果，可以通过改变或者读取我们所保存的值来实现。\n" +
                "2.1.2 界面说明\n" +
                "界面中使用的Swing组件相对简单，整个大窗口可以看作一个JFrame对象，在JFrame对象中，存放一个JPanel对象，我们需要为这个JPanel对象进行布局，将文本框（JTextField对象）与各个计算器按钮（JButton对象）添加到这个JPanel中。在添加计算器按钮的时候，我们可以使用GridLayout布局处理器来进行网格状布局，由于各个计算器按钮都是以网格状分布在界面中的，因此使用GridLayout非常适合。本章计算器的界面布局并不复杂，因此在这里不再详细描述。\n" +
                "2.2 流程描述\n" +
                "用户打开计算器后，在没有关闭计算器之前，可以通过鼠标点击“1”到“9”数字键和点击“+”、“-”、“*”、“/”键去输入要运算结果的算术式，再通过点击“=”、“sqrt”、“1/x”等键去直接获取计算结果，除外，还可以点击“MC”、“MR”、“MS”、“M+”键去清除、读取、保存、累加计算显示框中显示的数字，还有清除上次结果、清除所有结果、退格等操作。从图2.2中可以看出，计算器打开之后，就开始监听用户的鼠标动作，如果输入是关于计算结果或者“MC”、“MR”、“MS”、“M+”、“Backspace”、“CE”、“C”等操作指令，而且没有关闭计算器，就返回计算结果并显示，如果不是，则不计算结果。接下来再继续等待用户的输入。\n" +
                "本章的计算器并没有复杂的流程，只需要简单的操作，返回计算结果等。在实现计算器的过程中，我们需要注意的是，例如已经点击了某个数字，再点击运算符，那么程序需要记录之前选点击的数字，当用户再次点击运算符（非“=”）时，系统就需要将结果显示到文本框中。因此在开发计算器的时候，我们需要注意用户点击的具体顺序。\n" +
                "\n" +
                "图 2.2 计算流程\n" +
                "2.3 建立计算器对象\n" +
                "实现一个计算器，我们需要建立一系列的对象来实现，例如，计算界面我们要建立一个界面类，还需要建立一个专门负责处理加、减、乘、除的基本计算类，还需要一个负责处理计算功能的业务类。本小节中只讲解创建这三个基本的类，如果在开发的过程发现可以将一些行为或者属性放置到一个新的对象中，那么可以再建立这些对象来完成需要实现的功能或者操作。\n" +
                "本章主要设计四个类来完成计算器的功能，界面类（CalFrame）—主要用来显示计算器界面，功能类（CalService）—主要用于完成计算器中的逻辑功能，计算工具类（MyMath）—此类是工具类，用于处理大型数字的加减乘除，计算器类(Cal)—用于打开计算器，计算器中各个类的关系如图2.3所示，从图中可以看出，我们的界面类继承了java.swing.JFrame类，计算器类使用了界面类，界面类使用了功能类，功能类使用了MyMath工具类，下面章节将对这些计算器的相关类作详细介绍。\n" +
                "\n" +
                "图2.3 计算器类图\n" +
                "2.3.1 MyMath工具类\n" +
                "使用float，double两种浮点基本类型来进行计算，容易损失精度，所以，我们使用一个自己定义了加，减，乘，除方法的类，此类使用BigDecimal来封装基本类型，在不损失精度的同时，也可以进行超大数字的四则运算。为了方便调用，此类的方法全部都是静态方法，可以直接用“类名.方法名”调用，这个类包含以下方法：\n" +
                "\uF071static double add( double num1, double num2 )，加法，使用来计算结果的数字是封装后的num1和 num2，并返回double类型。\n" +
                "\uF071static double subtract ( double num1, double num2 )，减法，使用来计算结果的数字是封装后的num1和 num2，并返回double类型。\n" +
                "\uF071static double multiply ( double num1, double num2 )，乘法，使用来计算结果的数字是封装后的num1和 num2，并返回double类型。\n" +
                "\uF071static double divide ( double num1, double num2 )，除法，使用来计算结果的数字是封装后的num1和 num2，并返回double类型。\n" +
                "MyMath类提供了基础的四则运算方法，由于该类中所有的方法都是静态的，因此外界可以直接调用。在实现MyMath的过程中需要注意的是，这几个四则运算方法，参数都是double类型的，要进行运算的话，需要将double类型转换成一个BigDecimal对象，我们可以使用以下代码来创建一个BigDecimal对象：\n" +
                "new BigDecimal(String.valueOf(number));\n" +
                "2.3.2 CalService类\n" +
                "CalService类主要是用来处理计算器的业务逻辑，用户在操作计算器时，此类将计算结果，并且返回，并且，会记录计算器的状态（用户的上一步操作）。包含以下方法：\n" +
                "\uF071String callMethod( String cmd , String text )，调用方法并返回计算结果。\n" +
                "\uF071String cal( String text , boolean isPercent )，用来计算加、减、乘、除法，并返回封装成String内型的结果。参数text是显示框中的数字内容，boolean类型的参数isPercent代表是否有\"%\"运算，如果有，便加上去。\n" +
                "\uF071String setReciprocal( String text )，用来计算倒数，并返回封装成String内型的结果。\n" +
                "\uF071String sqrt( String text )，用来计算开方，并返回封装成String内型的结果。\n" +
                "\uF071String setOp( String cmd , String text )，设置操作符号。\n" +
                "\uF071String setNegative( String text )，设置正负数，当text是正数时，返回负数的数字字符串，反之，则返回正数的数字字符串。\n" +
                "\uF071String catNum( String cmd, String text )，连接输入的数字，每次点击数字，就把把新加的数字追加到后面，并封装成字符串返回。\n" +
                "\uF071String backSpace( String text )，删除最后一个字符，并返回结果。\n" +
                "\uF071String mCmd( String cmd, String text )，用来实现“M+”、“MC”、“MR”、“MS”与存储有关的功能。\n" +
                "\uF071String clearAll()，清除所有计算结果。\n" +
                "\uF071String clear( String text)，清除上次计算结果。\n" +
                "CalService类中的各个方法都是用于处理计算的逻辑，其中callMethod方法可以看作中一个中转的方法，根据参数中的cmd值进行分发处理，例如调用该方法时将“CE”字符串作为cmd，那么该方法就根据这个字符串再调用需要执行“CE”的方法。如果需要做更好的程序解耦，我们可以将这些做成一个状态模式，将各个计算的方法都抽象成一个计算接口，该接口提供一个计算的方法，然后按照具体的情况，为该接口提供不同的实现，例如计算开方、计算倒数等实现，然后向callMethod传入不同的实现类，直接调用接口方法。\n" +
                "2.3.3 CalFrame类\n" +
                "CalFrame类继承javax.swing.Jframe类，主要是用于计算器界面的实现，此类中，排版了计算器中各个组件的位置，为组件增加事件监听器，用来监听用户的操作，并做调用相应的方法，主要包含以下方法：\n" +
                "\uF071void initialize()，初始化计算器界面。\n" +
                "\uF071ActionListener getActionListener()，如果动作监听器为空，则创建一个，并返回，如果不为空，直接返回。\n" +
                "\uF071JTextField getTextField()，这个方法初始化输入框。\n" +
                "\uF071JButton[] getMButton()，此方法获得计算器的存储操作键。\n" +
                "\uF071JButton[] getRButton()，此方法获得计算器的结果操作键。\n" +
                "\uF071JButton[] getNButton()，此方法获得计算器的其它操作键。\n" +
                "由于CalFrame是界面类，因此所需要进行的业务处理并不多，更多的是监听用户的操作，并进行分发处理。这就有点像web应用中的MVC模式中的V（视图），并不处理任务的业务逻辑，主要职责是显示相应的数据。在本章中，CalFrame包括了一些监听器，监听界面事件并调用相关的业务方法，在实际开发中，我们可以将这些监听器作为MVC模式中的C（控制器）提取到另外的类中。\n" +
                "2.4 MyMath工具类实现\n" +
                "MyMath是一个工具类，主要用于处理加、减、乘、除四则运算，我们已经确定了实现这四个方法的时候，都使用BigDecimal对象进行计算。由于我们定义MyMath方法的时候，所有方法的参数都是double类刑的，因此我们可以提供一个工具方法来将double转换成BigDecimal类型。\n" +
                "以下代码根据一个double类型转换成一个BigDecimal。\n" +
                "代码清单：code\\cal\\src\\org\\crazyit\\cal\\MyMath.java\n" +
                "\t/**\n" +
                "\t * 为一个double类型的数字创建BigDecimal对象\n" +
                "\t * @param number\n" +
                "\t * @return\n" +
                "\t */\n" +
                "\tprivate static BigDecimal getBigDecimal(double number) {\n" +
                "\t\treturn new BigDecimal(number);\n" +
                "\t}\n" +
                "提供了这个工具方法后，我们可以在其他的计算方法中使用这个工具方法，选择将double的参数转换成BigDecimal对象，然后再进行具体的运算。\n" +
                "2.4.1 实现四则运算\n" +
                "编写了double转换的工具方法后，实现加、减、乘、除比较简单，由于BigDecimal已经为我们实现了，因此可以直接调用该类的相应方法即可实现，以下代码分别实现四则运算。\n" +
                "代码清单：code\\cal\\src\\org\\crazyit\\cal\\MyMath.java\n" +
                "加法：\n" +
                "\tpublic static double add(double num1, double num2) {\n" +
                "\t\t//调用工具方法将double转换成BigDecimal\n" +
                "\t\tBigDecimal first = getBigDecimal(num1);\n" +
                "\t\tBigDecimal second = getBigDecimal(num2);\n" +
                "\t\treturn first.add(second).doubleValue();\n" +
                "\t}\n" +
                "减法：\n" +
                "\tpublic static double subtract(double num1, double num2) {\n" +
                "\t\tBigDecimal first = getBigDecimal(num1);\n" +
                "\t\tBigDecimal second = getBigDecimal(num2);\n" +
                "\t\treturn first.subtract(second).doubleValue();\n" +
                "\t}\n" +
                "乘法：\n" +
                "\tpublic static double multiply(double num1, double num2) {\n" +
                "\t\tBigDecimal first = getBigDecimal(num1);\n" +
                "\t\tBigDecimal second = getBigDecimal(num2);\n" +
                "\t\treturn first.multiply(second).doubleValue();\n" +
                "\t}\n" +
                "除法：\n" +
                "\tpublic static double divide(double num1, double num2) {\n" +
                "\t\tBigDecimal first = getBigDecimal(num1);\n" +
                "\t\tBigDecimal second = getBigDecimal(num2);\n" +
                "\t\treturn first.divide(second, DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP)\n" +
                "\t\t\t\t.doubleValue();\n" +
                "\t}\n" +
                "四个方法都是调用了BigDecimal的方法来实现，Java的BigDecimal类为我们提供了许多强大的计算方法，可以让我们很方便的进行数学运算，除本章介绍的方法我，读者可以查阅Java的API来学习该类的详细使用。\n" +
                "2.5 计算器主界面\n" +
                "这里实现计算器的界面，是用java的Swing实现的，主要用到的类有javax.swing.JFrame（窗口），javax.swing.JButton（按钮），javax.swing.JTextField（输入框），并使用java.awt.BorderLayout和java.awt.GridLayout进行布局。在这里，我们使用“自下而下”的方法去观察此类，先看总体的排版实现，再看各个小组件的实现。为了方便布局，我们按相近的外观把计算器分为四个部分，见图2.4：\n" +
                "\n" +
                "图 2.4 布局\n" +
                "2.5.1 初始化界面（initialize()方法）\n" +
                "此类就是一个JFrame（继承了javax.swing.JFrame），用来做其它窗口或者组件的父容器，初始化计算器窗口的大概流程：\n" +
                "\uF071设置父窗口JFrame标题、布局管理器、是否可以改变等属性 。\n" +
                "\uF071增加输入与计算结果显示框。对应图2.4中的左上角那部分。\n" +
                "\uF071增加左边存储操作键。\n" +
                "\uF071增加结果操作键。\n" +
                "\uF071增加数字与其它运算符。\n" +
                "由于按外观相近的方式把组件分成了四部分，就方便程序中对相同属性的组件统一地创建与设置属性，对于界面的布局也更加地直观与方便，观察此图，我们可以使用BorderLayout做总体布局，如图2.5所示。\n" +
                "\n" +
                "图 2.5 布局管理器\n" +
                "以下代码设置父窗口JFrame标题和设置是否可以改变大小的属性。\n" +
                "//设置窗口的标题\n" +
                "this.setTitle(\"计算器\");\n" +
                "//设置为不可改变大小\n" +
                "this.setResizable( false );\n" +
                "增加输入与结果显示的JTextField输入框，这里调用本类的getTextField()方法获取，并把它加入panel中的NORTH位置中：\n" +
                "//增加计算输入框\n" +
                "JPanel panel = new JPanel();\n" +
                "panel.setLayout( new BorderLayout(10,1) );\n" +
                "panel.add( getTextField(), BorderLayout.NORTH );\n" +
                "panel.setPreferredSize( new Dimension( PRE_WIDTH, PRE_HEIGHT ) );\n" +
                "增加左边存储操作键，本类需要通过getMButton()方法获取一个保存JButton对象的数组，getMButton方法我们将在2.5.2中实现。获取数组后，遍历数组，并把数组中的元素加到一个新建的JPanel中，最后再把这个JPanel加到JFrame的相应位置：\n" +
                "\t\t//增加左边存储操作键\n" +
                "\t\tJButton[] mButton = getMButton();\n" +
                "\t\t//新建一个panel，用于放置按钮\n" +
                "\t\tJPanel panel1 = new JPanel();\n" +
                "\t\t//设置布局管理器\n" +
                "\t\tpanel1.setLayout( new GridLayout( 5, 1, 0, 5 ) );\n" +
                "\t\t//迭代增加按钮\n" +
                "\t\tfor( JButton b : mButton )\tpanel1.add(b);\n" +
                "增加结果操作键，这些结果操作键包括：Back，CE，C。通过本类的getRButton()方法获取一个保存JButton对象的数组，获取数组后，遍历数组，并把数组中的元素加到一个新建的JPanel中，最后再把这个JPanel加到JFrame相应的位置，具体实现的代码如下：\n" +
                "//增加结果操作键\n" +
                "JButton[] rButton = getRButton();\n" +
                "JPanel panel2 = new JPanel();\n" +
                "panel2.setLayout( new BorderLayout(1, 5) );\n" +
                "//新建一个panel，用于放置按钮\n" +
                "JPanel panel21 = new JPanel();\n" +
                "//设置布局管理器\n" +
                "panel21.setLayout( new GridLayout( 1, 3, 3, 3 ) );\n" +
                "//迭代增加按钮\n" +
                "for( JButton b : rButton ) panel21.add(b);\n" +
                "接下来将其他的按键加入到界面的JPanel对象中，这些操作键主要包括数字键和其他的一些运算键，我们同样的通过一个getNButton方法来返回这些操作键对应的JButton对象，最后将这些JButton对象加入到相应的JPanel中，加入到JPanel并设置相应布局的代码如下：\n" +
                "//增加数字与其它运算符\n" +
                "JButton[] nButton = getNButton();\n" +
                "//新建一个panel，用于放置按钮\n" +
                "JPanel panel22 = new JPanel();\n" +
                "//设置布局管理器\n" +
                "panel22.setLayout( new GridLayout( 4, 5, 3, 5 ) );\n" +
                "//迭代增加按钮\n" +
                "for( JButton b : nButton ) panel22.add(b);\n" +
                "//把新增加的面板加到frame\n" +
                "…\n" +
                "this.add(panel);\n" +
                "在本小节中，我们通过getMButton、getRButton和getNButton方法来返回不同的JButton数组，然后再对这些数组进行遍历，将每一个JButton加入到界面中。这一个返回JButton数组的方法并没有实现，下面将介绍如何实现这三个方法。\n" +
                "以上所有的代码均在code\\cal\\src\\org\\crazyit\\cal\\CalFrame.java中。\n" +
                "2.5.2 创建运算键\n" +
                "运算键主要包括数字键与基本运算键，数字键从0到9，基本运算键包括开方、正负、小数点等键，主要实现计算器界面的getNButton方法即可。以下是该方法的实现。\n" +
                "代码清单：code\\cal\\src\\org\\crazyit\\cal\\CalFrame.java\n" +
                "\tprivate JButton[] getNButton() {\n" +
                "\t\t// 这个数组保存需要设置为红色的操作符\n" +
                "\t\tString[] redButton = { \"/\", \"*\", \"-\", \"+\", \"=\" };\n" +
                "\t\tJButton[] result = new JButton[nOp.length];\n" +
                "\t\tfor (int i = 0; i < this.nOp.length; i++) {\n" +
                "\t\t\t// 新建按钮\n" +
                "\t\t\tJButton b = new JButton(this.nOp[i]);\n" +
                "\t\t\t// 为按钮增加事件\n" +
                "\t\t\tb.addActionListener(getActionListener());\n" +
                "\t\t\t// 对redButton排序，才可以使用binarySearch方法\n" +
                "\t\t\tArrays.sort(redButton);\n" +
                "\t\t\t// 如果操作符在redButton出现\n" +
                "\t\t\tif (Arrays.binarySearch(redButton, nOp[i]) >= 0) {\n" +
                "\t\t\t\tb.setForeground(Color.red);\n" +
                "\t\t\t} else {\n" +
                "\t\t\t\tb.setForeground(Color.blue);\n" +
                "\t\t\t}\n" +
                "\t\t\tresult[i] = b;\n" +
                "\t\t}\n" +
                "\t\treturn result;\n" +
                "\t}\n" +
                "以上代码需要注意的是，我们需要提供一个红色按键的字符串数组，在遍历所有的需要创建的按键数组时，就需要作判断，如果按键数组里面存在红色按键数组的某个元素，就需要调用JButton的setForeground方法来设置该按钮的字体颜色。在代码中我们不能看到该方法帮我们创建了哪些按键，代码中使用了一个nOp的字符串数组来保存需要创建的按键，该数组包含的内容如下：\n" +
                "\tprivate String[] nOp = { \"7\", \"8\", \"9\", \"/\", \"sqrt\", \"4\", \"5\", \"6\", \"*\",\n" +
                "\t\t\t\"%\", \"1\", \"2\", \"3\", \"-\", \"1/x\", \"0\", \"+/-\", \".\", \"+\", \"=\" };\n" +
                "2.5.3 创建操作按键\n" +
                "操作按键的创建与运算键的创建基本一致，只是所有的按键的字体都必须是红色的，创建操作按钮，我们需要实现getMButton和getRButton方法，以下是这两个方法的具体实现。\n" +
                "代码清单：code\\cal\\src\\org\\crazyit\\cal\\CalFrame.java\n" +
                "\tprivate JButton[] getMButton() {\n" +
                "\t\tJButton[] result = new JButton[mOp.length + 1];\n" +
                "\t\tresult[0] = getButton();\n" +
                "\t\tfor (int i = 0; i < this.mOp.length; i++) {\n" +
                "\t\t\t// 新建按钮\n" +
                "\t\t\tJButton b = new JButton(this.mOp[i]);\n" +
                "\t\t\t// 为按钮增加事件\n" +
                "\t\t\tb.addActionListener(getActionListener());\n" +
                "\t\t\t// 设置按钮颜色\n" +
                "\t\t\tb.setForeground(Color.red);\n" +
                "\t\t\tresult[i + 1] = b;\n" +
                "\t\t}\n" +
                "\t\treturn result;\n" +
                "\t}\n" +
                "\tprivate JButton[] getRButton() {\n" +
                "\t\tJButton[] result = new JButton[rOp.length];\n" +
                "\t\tfor (int i = 0; i < this.rOp.length; i++) {\n" +
                "\t\t\t// 新建按钮\n" +
                "\t\t\tJButton b = new JButton(this.rOp[i]);\n" +
                "\t\t\t// 为按钮增加事件\n" +
                "\t\t\tb.addActionListener(getActionListener());\n" +
                "\t\t\t// 设置按钮颜色\n" +
                "\t\t\tb.setForeground(Color.red);\n" +
                "\t\t\tresult[i] = b;\n" +
                "\t\t}\n" +
                "\t\treturn result;\n" +
                "\t}\n" +
                "getMButton创建的是界面左侧的操作键，getRButton创建的是运作键上面的操作键，getMButton和getRButton创建的操作键如下：\n" +
                "\t//getMButton\n" +
                "\tprivate String[] mOp = { \"MC\", \"MR\", \"MS\", \"M+\" };\n" +
                "\t//getRButton\n" +
                "\tprivate String[] rOp = { \"Back\", \"CE\", \"C\" };\n" +
                "创建完界面元素后，我们可以运行计算器，具体的效果如图2.4所示。\n" +
                "2.5.4 增加事件监听器\n" +
                "在上一节中，我们注意到程序为JButton类型的组件增加了事件监听器，这个事件监听器是用来响应用户的鼠标操作。我们使用java.awt.event.ActionListener接口来创建一个事件监听器，主要是实现接口中的actionPerformed( ActionEvent e )方法，当监听器监听到用户的操作时，会自动调用此方法，并在此方法中处理业务逻辑，再把数据返回显示给用户。见以下代码。\n" +
                "代码清单：code\\cal\\src\\org\\crazyit\\cal\\CalFrame.java\n" +
                "actionListener = new ActionListener(){\n" +
                "\tpublic void actionPerformed( ActionEvent e ) {\n" +
                "\t\tString cmd = e.getActionCommand();\n" +
                "\t\tString result = null;\n" +
                "\t\ttry {\n" +
                "\t\t\t//计算操作结果\n" +
                "\t\t\tresult = service.callMethod( cmd, textField.getText() );\n" +
                "\t\t} catch( Exception e1 ) {\n" +
                "\t\t\tSystem.out.println( e1.getMessage() );\n" +
                "\t\t}\n" +
                "\t\t//处理button的标记\n" +
                "\t\tif( cmd.indexOf(\"MC\") == 0 ) {\n" +
                "\t\t\tbutton.setText(\"\");\n" +
                "\t\t} else if( cmd.indexOf(\"M\") == 0 && service.getStore() > 0 ) {\n" +
                "\t\t\tbutton.setText(\"M\");\n" +
                "\t\t}\n" +
                "\t\t//设置计算结果\n" +
                "\t\tif( result != null ) {\n" +
                "\t\t\ttextField.setText( result );\n" +
                "\t\t}\n" +
                "\t}\t\t\t\t\n" +
                "};\n" +
                "从上面代码中可以看到，这里是通过实现java.awt.event.ActionListener接口中的actionPerformed( ActionEvent e )方法去创建一个java.awt.event.ActionListener类型的内部类，并在actionPerformed方法中处理业务逻辑。\n" +
                "首先，调用CalService实例中的callMethod方法去处理计算，并把结果返回。\n" +
                "result = service.callMethod( cmd, textField.getText() );\n" +
                "再设置标志存储结果类型的存储标记，如果是点击“MC”按钮，就把标记设置为空，如果是点击“MS”，“MR”，“M+”，并且存储结果大于0，就把标记设置为“M”，这里弄不明白的读者，可以先试着使用一下windows计算器的这几个按钮，再看这里就很容易理解了。\n" +
                "if( cmd.indexOf(\"MC\") == 0 ) {\n" +
                "\tbutton.setText(\"\");\n" +
                "} else if( cmd.indexOf(\"M\") == 0 && service.getStore() > 0 ) {\n" +
                "\tbutton.setText(\"M\");\n" +
                "}\n" +
                "最后把计算结果设置到结果文本显示框中，显示给使用者。\n" +
                "if( result != null ) {\n" +
                "\ttextField.setText( result );\n" +
                "}\n" +
                "在监听器中，我们调用了CalServer的callMethod方法来取得操作的结果，换言之，界面中的每次点击都会执行该方法，callMethod我们并没有提供任何实现，在下一小节，我们将实现该方法。\n" +
                "2.6 计算业务处理\n" +
                "在2.3章节中，我们建立了一个类名为CalService的类来处理计算器的计算业务，该类处理了整个应用中的大部分业务，其中包括数字运算，存储运算，操作结果等业务。有四个重要的属性：firstNum代表第一个操作数，secondNum代表第二个操作数，lastOp代表上次用户所做的操作, isSecondNum代表是否第二个操作数。\n" +
                "2.6.1 计算四则运算结果\n" +
                "在使用计算器计算加、减、乘、除法的过程中，正常的情况应该是用户先输入第一个操作数，再点击加、减、乘、除计算符号，再输入第二个操作数，最后点“=”号计算出结果，所以这时用firstNum去保存用户输入的第一个操作数，secondNum去保存第二个操作数，lastOp去保存计算符号或者其它操作，isSecondNum用来判断用户是在输入第几个操作数。\n" +
                "在用户输入数字的时候（包括“0123456789.”），首先判断是第一个操作数还是第二个，如果是第一个，就把用户新输入的数字追加到原来数字的后面，并做为结果返回；如果是第二个，直接返回结果，并把isSecondNum标志为false，用户继续输入数字的时候，就把数字追加到原来数字的后面做为结果返回，见以下代码。\n" +
                "代码清单：code\\cal\\src\\org\\crazyit\\cal\\CalService.java\n" +
                "public String catNum( String cmd, String text ) {\n" +
                "\tString result = cmd;\n" +
                "\t//如果目前的text不等于0\n" +
                "\tif( !text.equals(\"0\") )\t{\n" +
                "\t\tif( isSecondNum ) {\n" +
                "\t\t\t//将isSecondNum标志为false\n" +
                "\t\t\tisSecondNum = false;\n" +
                "\t\t} else {\n" +
                "\t\t\t//刚返回结果为目前的text加上新点击的数字\n" +
                "\t\t\tresult = text + cmd;\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\t//如果有.开头，刚在前面补0\n" +
                "\tif( result.indexOf(\".\") == 0 ) {\n" +
                "\t\tresult = \"0\" + result;\n" +
                "\t}\n" +
                "\treturn result;\n" +
                "}\n" +
                "当用户点击“+-*/”（四则运算）的时候，就把lastOp设置为其中一个符号，这个变量用来记录用户正要进行计算的类型，见以下代码。\n" +
                "代码清单：code\\cal\\src\\org\\crazyit\\cal\\CalService.java\n" +
                "public String setOp( String cmd , String text ) {\n" +
                "\t//将此操作符号设置为上次的操作\n" +
                "\tthis.lastOp = cmd;\n" +
                "\t//设置第一个操作数的值\n" +
                "\tthis.firstNum = text;\n" +
                "\t//将第二个操作数赋值为空\n" +
                "\tthis.secondNum = null;\n" +
                "\t//将isSecondNum标志为true\n" +
                "\tthis.isSecondNum = true;\n" +
                "\t//返回空值\n" +
                "\treturn null;\n" +
                "}\n" +
                "在上面的代码中，可以看到，除了设置lastOp外，还把输入的数字设置给firstNum，把secondNum设置为空，并把isSecondNum设置为true，代表下次输入数字时，要清空输入框并重新输入。\n" +
                "最后用户点击“=”号时，就是程序计算出最后结果的时候，此类的String cal( String text , boolean isPercent )方法实现了此计算，注意到这个方法的第二个参数isPercent，这是计算器的“%”号操作，如果有这种操作，第二个操作数就等于两数相乘再除以100，请看以下代码。\n" +
                "代码清单：code\\cal\\src\\org\\crazyit\\cal\\CalService.java\n" +
                "public String cal( String text , boolean isPercent )\n" +
                "\tthrows Exception {\n" +
                "\t//初始化第二个操作数\n" +
                "\tdouble secondResult = secondNum == null \n" +
                "\t\t? Double.valueOf( text ).doubleValue() \n" +
                "\t\t: Double.valueOf( secondNum ).doubleValue();\n" +
                "\t//如果除数为0，不处理\n" +
                "\tif( secondResult == 0 && this.lastOp.equals(\"/\") ) {\n" +
                "\t\treturn \"0\";\n" +
                "\t}\n" +
                "\t//如果有\"%\"操作，则第二个操作数等于两数相乘再除以100\n" +
                "\tif( isPercent ) {\n" +
                "\t\tsecondResult = MyMath.multiply( Double.valueOf( firstNum )\n" +
                "\t\t\t, MyMath.divide( secondResult, 100 ) ); \n" +
                "\t}\n" +
                "\t//四则运算，返回结果赋给第一个操作数\n" +
                "\tif( this.lastOp.equals(\"+\") ) {\n" +
                "\t\tfirstNum = String.valueOf( \n" +
                "\t\t\tMyMath.add( Double.valueOf( firstNum ), secondResult ) );\t\n" +
                "\t}\n" +
                "\telse if( this.lastOp.equals(\"-\") )\t{\n" +
                "\t\tfirstNum = String.valueOf( \n" +
                "\t\t\tMyMath.subtract( Double.valueOf( firstNum ), secondResult ) );\t\t\t\t\n" +
                "\t} else if( this.lastOp.equals(\"*\") ) {\n" +
                "\t\tfirstNum = String.valueOf( \n" +
                "\t\t\tMyMath.multiply( Double.valueOf( firstNum ), secondResult ) );\n" +
                "\t}\n" +
                "\telse if( this.lastOp.equals(\"/\") ) {\n" +
                "\t\tfirstNum = String.valueOf( \n" +
                "\t\t\tMyMath.divide( Double.valueOf( firstNum ),  secondResult ) );\t\t\t\t\n" +
                "\t}\n" +
                "\t//给第二个操作数重新赋值\n" +
                "\tsecondNum = secondNum == null ? text : secondNum;\n" +
                "\t//把isSecondNum标志为true\n" +
                "\tthis.isSecondNum = true;\n" +
                "\treturn firstNum;\n" +
                "}\n" +
                "上面计算结果中，经历了几个步骤，首先，确定secondNum的值，如果secondNum为空，secondNum就等于最后输入的数字，如果不为空，则等于原来的值，如果有“%”号操作，则secondNum再等于两数相乘除以100的结果；然后根据lastOp的值（+、-、*、/）去调用MyMath类中的add、subtract、multiply、divide方法，并把返回的结果保存到firstNum；最后把secondNum设置为空，把firstNum当做结果返回。\n" +
                "2.6.2 存储操作\n" +
                "定义一个double类型的属性store来充当存储器，在用户点击“MC（清除）”、“M+（累加）”、“MR（读取）”、“MS（保存）”操作时，就调用此方法，再根据得到的字符串去进行清除、累加、读取、保存操作，见以下代码。\n" +
                "代码清单：code\\cal\\src\\org\\crazyit\\cal\\CalService.java\n" +
                "public String mCmd( String cmd, String text ) {\n" +
                "\tif( cmd.equals( \"M+\" ) ) {\n" +
                "\t\t//如果是\"M+\"操作,刚把计算结果累积到store中\n" +
                "\t\tstore = MyMath.add( store, Double.valueOf( text ) );\n" +
                "\t} else if( cmd.equals( \"MC\" ) ) {\n" +
                "\t\t//如果是\"MC\"操作，则清除store\n" +
                "\t\tstore = 0;\n" +
                "\t} else if( cmd.equals( \"MR\" ) ) {\n" +
                "\t\t//如果是\"MR\"操作，则把store的值读出来\n" +
                "\t\tisSecondNum = true;\n" +
                "\t\treturn String.valueOf( store );\n" +
                "\t} else if( cmd.equals( \"MS\" ) ) {\n" +
                "\t\t//如果是\"MS\"操作，则把计算结果保存到store\n" +
                "\t\tstore = Double.valueOf( text ).doubleValue();\n" +
                "\t}\n" +
                "\treturn null;\n" +
                "}\n" +
                "程序中提供了一个store的属性用来保存计算结果，当用户点击了“M+”时，就将结果加到store中，点击了“MC”时，就将store设置为0，点击了“MR”，则将store的值读取，点击了“MS”，则将store设置为当前的结果。\n" +
                "2.6.3 实现开方、倒数等\n" +
                "开方与倒数的计算实现都比较简单，开方是直接使用Math类的sqrt方法去计算接收到的数值，并且返回结果：\n" +
                "\tpublic String sqrt(String text) {\n" +
                "\t\t// 将isSecondNum标志为true\n" +
                "\t\tthis.isSecondNum = true;\n" +
                "\t\t// 计算结果并返回\n" +
                "\t\treturn String.valueOf(Math.sqrt(Double.valueOf(text)));\n" +
                "\t}\n" +
                "倒数是调用MyMath的divide方法去计算1与接收到的数值相除的值。\n" +
                "代码清单：code\\cal\\src\\org\\crazyit\\cal\\CalService.java\n" +
                "\tpublic String setReciprocal(String text) {\n" +
                "\t\t// 如果text为0，则不求倒数\n" +
                "\t\tif (text.equals(\"0\")) {\n" +
                "\t\t\treturn text;\n" +
                "\t\t} else {\n" +
                "\t\t\t// 将isSecondNum标志为true\n" +
                "\t\t\tthis.isSecondNum = true;\n" +
                "\t\t\t// 计算结果并返回\n" +
                "\t\t\treturn String.valueOf(MyMath.divide(1, Double.valueOf(text)));\n" +
                "\t\t}\n" +
                "\t}\n" +
                "2.6.4 实现倒退操作\n" +
                "当我们的程序中得到用户在界面输入的相关数字时，如果用户进行了倒退操作，我们可以将用户输入的数字进行截取，如果接收到的字符串是“0”或者为null，则不作任何操作，直接返回，否则，我们将使用String的substring方法进行处理，将输入字符串的最后一位截取。以下方法实现倒退操作。\n" +
                "代码清单：code\\cal\\src\\org\\crazyit\\cal\\CalService.java\n" +
                "\tpublic String backSpace(String text) {\n" +
                "\t\treturn text.equals(\"0\") || text.equals(\"\") ? \"0\" : text.substring(0,\n" +
                "\t\t\t\ttext.length() - 1);\n" +
                "\t}\n" +
                "2.6.5 清除计算结果\n" +
                "清除所有计算结果，把firstNum与secondNum都设置为原始值，并返回firstNum，在CalService中提供了一个clearAll方法，用于清除所有的计算结果。\n" +
                "代码清单：code\\cal\\src\\org\\crazyit\\cal\\CalService.java\n" +
                "\tpublic String clearAll() {\n" +
                "\t\t// 将第一第二操作数恢复为默认值\n" +
                "\t\tthis.firstNum = \"0\";\n" +
                "\t\tthis.secondNum = null;\n" +
                "\t\treturn this.firstNum;\n" +
                "\t}\n" +
                "2.6.6 实现中转方法（callMethod）\n" +
                "在前面的章节中，我们已经实现了各个方法，例如四则运算、开方、倒数、清除计算等，但是在界面的监听器中，只会调用CalService的callMethod方法进行运算，因此我们需要对callMethod进行相关的实现。\n" +
                "代码清单：code\\cal\\src\\org\\crazyit\\cal\\CalService.java\n" +
                "\tpublic String callMethod(String cmd, String text) throws Exception {\n" +
                "\t\tif (cmd.equals(\"C\")) {\n" +
                "\t\t\treturn clearAll();\n" +
                "\t\t} else if (cmd.equals(\"CE\")) {\n" +
                "\t\t\treturn clear(text);\n" +
                "\t\t} else if (cmd.equals(\"Back\")) {\n" +
                "\t\t\treturn backSpace(text);\n" +
                "\t\t} else if (numString.indexOf(cmd) != -1) {\n" +
                "\t\t\treturn catNum(cmd, text);\n" +
                "\t\t} else if (opString.indexOf(cmd) != -1) {\n" +
                "\t\t\treturn setOp(cmd, text);\n" +
                "\t\t} else if (cmd.equals(\"=\")) {\n" +
                "\t\t\treturn cal(text, false);\n" +
                "\t\t} else if (cmd.equals(\"+/-\")) {\n" +
                "\t\t\treturn setNegative(text);\n" +
                "\t\t} else if (cmd.equals(\"1/x\")) {\n" +
                "\t\t\treturn setReciprocal(text);\n" +
                "\t\t} else if (cmd.equals(\"sqrt\")) {\n" +
                "\t\t\treturn sqrt(text);\n" +
                "\t\t} else if (cmd.equals(\"%\")) {\n" +
                "\t\t\treturn cal(text, true);\n" +
                "\t\t} else {\n" +
                "\t\t\treturn mCmd(cmd, text);\n" +
                "\t\t}\n" +
                "\t}\n" +
                "CalService中的callMethod方法，只是判断输入命令，再决定调用具体的哪个方法处理计算。例如监听器监听到用户点击了倒退了按键，那么callMethod方法就会根据点击的按键文本来找到backSpace方法。当然，使用这么多的if…else…并不是最佳的解决方案，我们可以使用一些的设计模式来解决。有兴趣的读者可以了解相关的设计模式，考虑如何解决这些问题。\n" +
                "\t2.7 本章小结\n" +
                "    本章主要是通过一个仿Windows计算器的基本实现，向读者讲解Java swing编程，示范了JFrame，JPanel，JTextField，JButton的使用。界面布局方面，使用到了awt的BorderLayout与GridLayourt布局管理器去布局。并且向读者介绍了ActionLisner事件监听器的使用，介绍如何监听用户的动作响应用户，并且向用户返回有用的信息。本章中实现的计算相对较为简单，有兴趣的读者可以在本文的基础上实现更强大的计算器（科学型计算器）。另外需要注意的是，本章程序编写的过程中，使用了许多if…else…语句，对设计模式有一定了解或者希望对此有了解的读者，可以尝试去重构本章的代码，消除这些if…else…。在下面的章节中，我们会在编写的过程中，展示一些设计模式的概念。')");
        db.execSQL("insert into book_chapter(book_english_name,chapter_name,chapter_id,content)values('crazy_java','第3章 图片浏览器','3','3.1 图片浏览器概述\n" +
                "相信使用Window操作系统的大多数用户，都使用过Windows的图片浏览器，或者是功能更强大与复杂的ACDSee图片浏览器（这个还支持编辑图片），图片浏览器最基本的功能是能浏览一个目录中的所有图片，并可以点击浏览上一张图片或者下一张图片，还有对图片放大与缩小，或者翻转图片等操作，在这里，实现了图片的浏览功能，导航功能（下一张、上一张），放大缩小功能。\n" +
                "本章将实现一个最简单的图片浏览器，包括了打开图片、放大与缩小图片、查看上一张和下一张图片等功能，图片浏览器的最终效果如图3.1所示。\n" +
                "\n" +
                "图 3.1 图片浏览器\n" +
                "3.2 创建图片浏览器的相关对象\n" +
                "我们首先需要创建图片浏览器的相关对象。我们先创建图片浏览器的界面对象ViewerFrame，然后在该类中，我们为菜单、按钮加了事件监听器，所以定义了一个继承AbstractAction的类ViewerAction来响应这些动作。在Action中响应动作，就到处理具体逻辑的步骤，我们把所有的逻辑处理放到ViewerService类中，ViewerService中包括打开图片、上一张、下一张、放大和缩小图片等功能，为了程序更好的解耦合，我们可以把具体的某些业务处理放置到独立的类中进行处理。\n" +
                "除了以上所说的几个类，由于我们这个程序有打开图片的操作，所以需要一个文件过滤器（只能选择图片类型的文件），所以定义了一个继承JFileChooser的类ViewChooser，这个类里面定义了自己的文件过滤器。本章中所涉及的对象及它们之间的关系如图3.2所示。\n" +
                "\n" +
                "图 3.2 图片浏览器类图\n" +
                "本章程序的功能较为简单，因此所涉及的对象也并不复杂，只有简单的五个对象。\n" +
                "3.2.1 文件过滤器\n" +
                "如果要使文件对话框实现文件过滤功能，就需要结合FileFilter类来进行文件操作，文件过滤器是FileFilter的一个继承，也是文件对话框的内部类，里面重写了FileFilter的accept与getDescription方法：\n" +
                "\uF071boolean accept( File f )，判断文件是否属于图片类型。\n" +
                "\uF071String getDescription()，获取过滤器的描述。\n" +
                "文件过滤器主要在用户打开图片时使用，当用户进行了图片选择后，就可以对用户所选择的文件进行验证。当用户打开文件选择时，我们就可以对所有的文件进行一次过滤，文件选择器中只可以选择我们所定义的图片文件，那么其他的文件将不会被显示。在本章中，文件过滤器是文件对话框类（ViewerFileChooser）的一个内部类（MyFileFilter）。\n" +
                "3.2.2 文件对话框\n" +
                "Java文件对话框的实现比较简单，只要使用JFileChooser类并提供一个自己的构造器即可。这里的文件对话框对象是JFileChooser类的子类，目的是为了加入在3.2.1中定义的文件过滤器：\n" +
                "\uF071void addFilter()，为这个文件对话框增加过滤器。\n" +
                "该对象中的addFilter方法主要用于向文件对话框加入文件过滤器，例如我们需要只显示.bmp的文件，那么可以在addFilter方法中使用以下代码实现：\n" +
                "\t\tthis.addChoosableFileFilter(new MyFileFilter(new String[] { \".BMP\" },\n" +
                "\t\t\t\t\"BMP (*.BMP)\"));\n" +
                "在文件对话框的addFilter方法加入以上的代码后，那么用户将不能看到.bmp的文件，并且在“文件类型”的下拉中也只能选择.bmp，效果如图3.3所示。在本章中，文件对话框对应的是ViewerFileChooser类。\n" +
                "\n" +
                "图3.3 文件过滤器的作用\n" +
                "3.2.3 主界面类\n" +
                "我们建立一个界面类作为图片浏览器的主界面，该类包括图片显示区、菜单栏、工具栏，并为工具栏与菜单栏加上事件监听器，如下：\n" +
                "\uF071void init()，初始化图片浏览器的界面。\n" +
                "\uF071JLabel getLabel()，获取显示图片的JLabel。\n" +
                "\uF071createToolPanel()，创建放大、缩小、上一张、下一张等工具按钮。\n" +
                "\uF071void createMenuBar()，创建文件、工具、帮助等菜单。\n" +
                "在这里需要注意的是，由于打开的图片大小并不能确定，因此图片显示区必须使用JScrollPane。在本章中，主界面对应的是ViewerFrame类。\n" +
                "3.2.4 业务处理类ViewerService\n" +
                "业务处理类主要是处理图片浏览器的大部分业务逻辑，包括打开图片、关闭浏览器、放大图片、缩小图片、浏览上一张图片、浏览下一张图片等功能，如下： \n" +
                "\uF071static ViewerService getInstance()，获取ViewerService类的一个单态实例。\n" +
                "\uF071void open( ViewerFrame frame )，弹出文件选择框，并读取被选择到的图片。\n" +
                "\uF071void zoom( ViewerFrame frame, boolean isEnlarge )，对正在浏览到的图片做放大或者缩小操作，这里可能会丢失图片精度。\n" +
                "\uF071void last( ViewerFrame frame )，浏览上一张图片。\n" +
                "\uF071next( ViewerFrame frame )，浏览下一张图片。\n" +
                "\uF071void menuDo( ViewerFrame frame, String cmd )，响应菜单的动作。\n" +
                "在本章中，这个业务处理类并不是无状态的Java对象，也就是意味着本章的业务处理类将人保存一些业务状态，这些业务状态包括：当前浏览的文件目录、文件目录的文件集合、图片放大或者缩小的比例等属性。由于我们这个是有状态的Java对象，那么就意味着，如果访问的是同一个实例，那么该对象的这些属性将会被所有的访问者共享，如果其中的一个访问者改变了其中一个或者多个属性，那么其他的访问者将会受到影响。当然，我们本章只是一个普通的图片浏览器，不存在多个用户使用同一个图片浏览器的情况。在本章中，业务处理类对应的是ViewerService类。\n" +
                "3.2.5 操作处理类\n" +
                "在本例中，由于用户可以执行的操作较少，因此，我们可以提供一个操作处理类来接收用户所有的操作，本例中的操作处理类是AbstractAction的一个子类，能用ImageIcon（图标）来创建一个Action，再用这个Action来创建按钮，点击按钮的时候，将调用此类的actionPerformed方法：\n" +
                "\uF071void actionPerformed( ActionEvent e )，重写AbstractAction的方法，响应事件。\n" +
                "由于我们只有一个操作处理类，因此在实现actionPerformed方法时，我们就需要进行一系列的判断，让程序知道用户进行了何种操作，再调用业务处理类中的相应方法。\n" +
                "到此，图片浏览器的相关对象都已经建立，并且确定了我们需要实现哪些方法，我们在实现的过程中，如果发现可以对程序进行重构，那么也可以在重构的过程中，创建相关的类。\n" +
                "3.3 创建主界面\n" +
                "这个图片浏览器的界面排版比较简单，只有菜单（不需要排版）、工具栏、图片显示区，我们使用BorderLayout进行布局，把工具栏放在BorderLayout.NORTH，把图片显示区放在BorderLayout.CENTER。在本章中，由于打开图片的大小并不确定，因此我们需要使用一个JScrollPane来作为图片显示区域。\n" +
                "3.3.1 初始化界面（init()方法）\n" +
                "首先，设置JFrame窗口的标题，接下来初始化画图区域，初始化为白色，然后再获取PENCIL_TOOL(铅笔)类型的Tool，创建各种鼠标监听器，并在监听的执行方法中调用Tool的相应方法，最后获取左边工具栏面板、下面菜单栏面板、菜单，并把这些面板与画图获取加到JFrame中，见以下代码。\n" +
                "代码清单：code\\viewer\\src\\org\\crazyit\\viewer\\ViewerFrame.java\n" +
                "public void init()\t{\n" +
                "\t\t//设置标题\n" +
                "\t\tthis.setTitle( \"看图程序\" );\n" +
                "\t\t//设置大小\n" +
                "\t\tthis.setPreferredSize( new Dimension( width, height ) );\n" +
                "\t\t//创建菜单\n" +
                "\t\tcreateMenuBar();\n" +
                "\t\t//创建工具栏\n" +
                "\t\tJPanel toolBar = createToolPanel();\n" +
                "\t\t//把工具栏和读图区加到JFrame里面\n" +
                "\t\tthis.add( toolBar, BorderLayout.NORTH );\n" +
                "\t\tthis.add( new JScrollPane(label), BorderLayout.CENTER );\n" +
                "\t\t//设置为可见\n" +
                "\t\tthis.setVisible( true );\n" +
                "\t\tthis.pack();\n" +
                "\t}\n" +
                "首先是为JFrame设置标题，接下来设置大小，然后调用本类的createMenuBar()方法去创建菜单栏、调用createToolPanel()方法去创建工具栏，最后把菜单栏和图片显示区加到JFrame中（图片显示区只是一个JLabel）。以上代码中的黑体部分，使用一个createToolPanel的方法来创建菜单，该方法将在下面章节中实现。\n" +
                "3.3.2 创建菜单栏\n" +
                "菜单栏，必须有事件响应，所以，先为菜单定义一个事件监听器，见以下代码。\n" +
                "代码清单：code\\viewer\\src\\org\\crazyit\\viewer\\ViewerFrame.java\n" +
                "//加给菜单的事件监听器\n" +
                "\tActionListener menuListener = new ActionListener(){\n" +
                "\t\tpublic void actionPerformed(ActionEvent e) {\n" +
                "\t\t\tservice.menuDo( ImageFrame.this, e.getActionCommand() );\n" +
                "\t\t}\n" +
                "\t};\n" +
                "这个事件监听器实现了ActionListener中的actionPerformed方法，是响应用户操作的方法，方法里面的service类就是我们的业务逻辑处理类ImageService的一个单态实例。有了这个事件监听器，就可以一次性创建出所有的菜单（用数组定义好菜单文字等东西的形式），见以下方法。\n" +
                "代码清单：code\\viewer\\src\\org\\crazyit\\viewer\\ViewerFrame.java\n" +
                "public void createMenuBar() {\n" +
                "\t\t//创建一个JMenuBar放置菜单\n" +
                "\t\tJMenuBar menuBar = new JMenuBar();\n" +
                "\t\t//菜单文字数组，以下面的menuItemArr一一对应\n" +
                "\t\tString[] menuArr = { \"文件(F)\", \"工具(T)\", \"帮助(H)\" };\n" +
                "\t\t//菜单项文字数组\n" +
                "\t\tString[][] menuItemArr = {\n" +
                "\t\t\t{\"打开(O)\",\"-\", \"退出(X)\"}, \n" +
                "\t\t\t{\"放大(M)\", \"缩小(O)\",\"-\",\"上一个(X)\",\"下一个(P)\"},\n" +
                "\t\t\t{ \"帮助主题\", \"关于\" }\n" +
                "\t\t};\n" +
                "\t\t//遍历menuArr与menuItemArr去创建菜单\n" +
                "\t\tfor( int i = 0 ; i < menuArr.length ; i++ ) {\n" +
                "\t\t\t//新建一个JMenu菜单\n" +
                "\t\t\tJMenu menu = new JMenu( menuArr[i] );\n" +
                "\t\t\tfor( int j = 0 ; j < menuItemArr[i].length ; j++ ) {\n" +
                "\t\t\t\t//如果menuItemArr[i][j]等于\"-\"\n" +
                "\t\t\t\tif ( menuItemArr[i][j].equals( \"-\" ) ) {\n" +
                "\t\t\t\t\t//设置菜单分隔\n" +
                "\t\t\t\t\tmenu.addSeparator();\n" +
                "\t\t\t\t} else {\n" +
                "\t\t\t\t\t//新建一个JMenuItem菜单项\n" +
                "\t\t\t\t\tJMenuItem menuItem = new JMenuItem( menuItemArr[i][j] );\n" +
                "\t\t\t\t\tmenuItem.addActionListener( menuListener );\n" +
                "\t\t\t\t\t//把菜单项加到JMenu菜单里面\n" +
                "\t\t\t\t\tmenu.add( menuItem );\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t\t//把菜单加到JMenuBar上\n" +
                "\t\t\tmenuBar.add(menu);\n" +
                "\t\t}\n" +
                "\t\t//设置JMenubar\n" +
                "\t\tthis.setJMenuBar( menuBar );\n" +
                "\t}\n" +
                "图片浏览器的菜单是这样的结构：\n" +
                "文件(F)\n" +
                "打开(O)\n" +
                "退出(X)\n" +
                "工具(T)\n" +
                "    放大(M)\n" +
                "缩小(O)\n" +
                "上一个(X)\n" +
                "下一个(P)\n" +
                "帮助(H)\n" +
                "    帮助主题\n" +
                "    关于\n" +
                "    从代码中可以看到，程序用两个数组把这两层菜单的文字保存了进去，两个数组一起遍历，每次都创建一个菜单项（JMenuItem），并为这个菜单项增加上前面定义的事件监听器，然后把这个菜单项加到JMenu中。每次遍历完 第一个数组，都把这个JMenu加到JMenuBar中。遍历完所有数组，就把这个JmenuBar加到JFrame里面，创建菜单的过程就完成了。\n" +
                "3.3.3 创建工具栏\n" +
                "这里的工具按钮，为了美观，想用图片的方式创建JButton，这里就要用到AbstractAction，也就是我们扩展的ViewerAction类，首先是用ViewerAction的ViewrAction(ImageIcon icon, String name, ViewerFrame frame)去创建一个ViewrAction，参数里面的icon对象就是从本地路径中读了图标的图标类，然后以这个ViewerAction对象为参数去创建一个JButton。见以下代码。\n" +
                "代码清单：code\\viewer\\src\\org\\crazyit\\viewer\\ViewerFrame.java\n" +
                "public JPanel createToolPanel() {\n" +
                "\t\t//创建一个JPanel\n" +
                "\t\tJPanel panel = new JPanel();\n" +
                "\t\t//创建一个标题为\"工具\"的工具栏\n" +
                "\t\tJToolBar toolBar = new JToolBar( \"工具\" );\n" +
                "\t\t//设置为不可拖动\n" +
                "\t\ttoolBar.setFloatable( false );\n" +
                "\t\t//设置布局方式\n" +
                "\t\tpanel.setLayout( new FlowLayout( FlowLayout.LEFT ) );\n" +
                "\t\t//工具数组\n" +
                "\t\tString[] toolarr = { \"open\", \"last\", \"next\", \"big\", \"small\" };\n" +
                "\t\tfor( int i = 0 ; i < toolarr.length ; i++ ) {\n" +
                "\t\t\tViewerAction action = new ViewerAction( \n" +
                "\t\t\t\tnew ImageIcon(\"img/\" + toolarr[i] + \".gif\")\n" +
                "\t\t\t\t, toolarr[i], this );\n" +
                "\t\t\t//以图标创建一个新的button\n" +
                "\t\t\tJButton button = new JButton( action );\n" +
                "\t\t\t//把button加到工具栏中\n" +
                "\t\t\ttoolBar.add(button);\n" +
                "\t\t}\n" +
                "\t\tpanel.add( toolBar );\n" +
                "\t\t//返回\n" +
                "\t\treturn panel;\n" +
                "\t}\n" +
                "以上代码的黑体部分，我们使用了JButton来创建工具栏的图标，每一个JButton对象都使用ViewerAction作为构造参数，但是需要注意的是，各个JButton之间并不是共享一个ViewerAction的实例。创建完菜单与工具栏后，可以运行查看具体的效果，主界面的效果如图3.4所示。\n" +
                "\n" +
                "图3.4 图片浏览器主界面\n" +
                "在本例中，图片浏览器的功能相对较为简单，因此界面也是较为简洁。如果想做更强大的图片浏览器，可以参考ACESee或者Windows图片浏览器等功能。\n" +
                "3.4 实现图片浏览的操作\n" +
                "ViewerService类主要是处理图片浏览器的大部分业务逻辑，包括打开图片、关闭浏览器、放大图片、缩小图片、浏览上一张图片、浏览下一张图片等功能，在这里需要再做一次说明，ViewerService是有状态的Java对象。\n" +
                "3.4.1 实现工具栏点击\n" +
                "我们在3.2.5中创建了一个ViewerAction的类，主要用于处理工具栏的点击事件，当用户点击了工具栏的某个操作时，就会执行ViewerAction的actionPerformed的方法。我们在3.3.3中创建工具栏时，使用了以下代码。\n" +
                "代码清单：code\\viewer\\src\\org\\crazyit\\viewer\\ViewerFrame.java\n" +
                "\t\tString[] toolarr = { \"open\", \"last\", \"next\", \"big\", \"small\" };\n" +
                "\t\tfor( int i = 0 ; i < toolarr.length ; i++ ) {\n" +
                "\t\t\tViewerAction action = new ViewerAction( \n" +
                "\t\t\t\tnew ImageIcon(\"img/\" + toolarr[i] + \".gif\")\n" +
                "\t\t\t\t, toolarr[i], this );\n" +
                "\t\t\t//以图标创建一个新的button\n" +
                "\t\t\tJButton button = new JButton( action );\n" +
                "\t\t\t//把button加到工具栏中\n" +
                "\t\t\ttoolBar.add(button);\n" +
                "\t\t}\n" +
                "以上代码中使用了“open”、“last”等字符串用来标识应该使用ViewerService的哪个方法，那么就意味着我们需要在actionPerformed方法中作出这些判断：\n" +
                "\t\tif (this.name.equals(\"open\")) {\n" +
                "\t\t\t//打开文件对话框\n" +
                "\t\t} else if (this.name.equals(\"last\")) {\n" +
                "\t\t\t//上一下图片\n" +
                "\t\t}\n" +
                "\t\t…\n" +
                "本章中只有5个Action，就需要写5次的if…else，对于这样的代码，我们在本书的第二章（仿Windows计算器）中已经出现，当前并没有提供任何的解决方案，但是如果程序中出现如些之多的if…else，那么我们就需要想办法去解决。接下来，创建一个Action的接口，提供一个execute的方法。\n" +
                "代码清单：code\\viewer\\src\\org\\crazyit\\viewer\\action\\Action.java\n" +
                "public interface Action {\n" +
                "\t/**\n" +
                "\t * 具体执行的方法\n" +
                "\t * @param service 图片浏览器的业务处理类\n" +
                "\t * @param frame 主界面对象\n" +
                "\t */\n" +
                "\tvoid execute(ViewerService service, ViewerFrame frame);\n" +
                "}\n" +
                "编写了接口Action后，我们定义了一个execute的方法，那么，我们可以为该Action新建实现类，例如有一个打开文件对话框的Action，那么我们就新建一个OpenAction，该类实现Action接口。以下代码是OpenAction的具体的实现。\n" +
                "代码清单：code\\viewer\\src\\org\\crazyit\\viewer\\action\\ OpenAction.java\n" +
                "\tpublic void execute(ViewerService service, ViewerFrame frame) {\n" +
                "\t\t//打开文件对话框\n" +
                "\t}\n" +
                "提供了这个OpenAction后，我们需要修改创建工具栏的代码，换一种方式创建工具栏。\n" +
                "代码清单：code\\viewer\\src\\org\\crazyit\\viewer\\ViewerFrame.java\n" +
                "\t\t// 工具数组\n" +
                "\t\tString[] toolarr = { \"org.crazyit.viewer.action.OpenAction\", \n" +
                "\t\t\t\t\"org.crazyit.viewer.action.LastAction\", \n" +
                "\t\t\t\t\"org.crazyit.viewer.action.NextAction\", \n" +
                "\t\t\t\t\"org.crazyit.viewer.action.BigAction\", \n" +
                "\t\t\t\t\"org.crazyit.viewer.action.SmallAction\" };\n" +
                "\t\tfor (int i = 0; i < toolarr.length; i++) {\n" +
                "\t\t\tViewerAction action = new ViewerAction(new ImageIcon(\"img/\"\n" +
                "\t\t\t\t\t+ toolarr[i] + \".gif\"), toolarr[i], this);\n" +
                "\t\t\t// 以图标创建一个新的button\n" +
                "\t\t\tJButton button = new JButton(action);\n" +
                "\t\t\t// 把button加到工具栏中\n" +
                "\t\t\ttoolBar.add(button);\n" +
                "\t\t}\n" +
                "将原来的字符串更换为某个Action实现类的全限定类名，那么在构造ViewerAction的时候，就可以使用这个参数去创建具体的某个实现类。为ViewerAction编写一个工具方法，使用反射得到Action接口的某个实现类。\n" +
                "代码清单：code\\viewer\\src\\org\\crazyit\\viewer\\ViewerAction.java\n" +
                "\tprivate Action getAction(String actionName) {\n" +
                "\t\ttry {\n" +
                "\t\t\tif (this.action == null) {\n" +
                "\t\t\t\t//创建Action实例\n" +
                "\t\t\t\tAction action = (Action)Class.forName(actionName).newInstance();\n" +
                "\t\t\t\tthis.action = action;\n" +
                "\t\t\t}\n" +
                "\t\t\treturn this.action;\n" +
                "\t\t} catch (Exception e) {\n" +
                "\t\t\treturn null;\n" +
                "\t\t}\n" +
                "\t}\n" +
                "以上的黑体代码，使用了反射来创建一个实例，并且该实例在ViewerAction中只有一个实例，由于该方法在ViewerAction中，所以我们在构造ViewerAction的时候，将对应的处理类传入即可。得到具体的某个Action实现类后，在实现ViewerAction的时候，我们就可以不必使用那堆烦人的if…else了，直接通过以上的工具方法（getAction）得到相关的Action实现类，再调用Action的execute方法即可。\n" +
                "代码清单：code\\viewer\\src\\org\\crazyit\\viewer\\ViewerAction.java\n" +
                "\tpublic void actionPerformed(ActionEvent e) {\n" +
                "\t\tViewerService service = ViewerService.getInstance();\n" +
                "\t\tAction action = getAction(this.actionName);\n" +
                "\t\t//调用Action的execute方法\n" +
                "\t\taction.execute(service, frame);\n" +
                "\t}\n" +
                "其实在本章中，我们并不需要如此复杂来实现，或许有些读者会觉得，编写多几个if…else可能比这样做更省事，但是，如果站在程序可扩展的角度看，当需要为图片浏览器添加行为时，我们就不必再修改ViewerAction，我们这样做，无论添加或者减少多少个Action，都不必去修改ViewerAction类，只需要去修改使用者（主界面对象）。对于一些简单的程序，我们可以使用if…else来解决，但是没有人知道程序将会有多复杂，因此笔者还是推崇使用其他方法来减少if…else或者尽量减低程序的耦合。\n" +
                "3.4.2 实现菜单的点击\n" +
                "我们为菜单增加了事件监听器，每次点击菜单时，都会先调用这个方法，由这个方法去决定做些什么类型的业务处理。在方法中，是根据菜单的文字去判断下步要调用的方法。\n" +
                "代码清单：code\\viewer\\src\\org\\crazyit\\viewer\\ViewerFrame.java\n" +
                "public void menuDo( ViewerFrame frame, String cmd ) {\n" +
                "\t\t//打开\n" +
                "\t\tif( cmd.equals(\"打开(O)\") ) {\n" +
                "\t\t\topen( frame );\n" +
                "\t\t}\n" +
                "\t\t//放大\n" +
                "\t\tif( cmd.equals(\"放大(M)\") ) {\n" +
                "\t\t\tzoom( frame, true );\n" +
                "\t\t}\n" +
                "\t\t//缩小\n" +
                "\t\tif( cmd.equals(\"缩小(O)\") )\t{\n" +
                "\t\t\tzoom( frame, false );\n" +
                "\t\t}\n" +
                "\t\t//上一个\n" +
                "\t\tif( cmd.equals(\"上一个(X)\") ){\n" +
                "\t\t\tlast( frame );\n" +
                "\t\t}\n" +
                "\t\t//下一个\n" +
                "\t\tif( cmd.equals(\"下一个(P)\") ) {\n" +
                "\t\t\tnext( frame );\n" +
                "\t\t}\n" +
                "\t\t//退出\n" +
                "\t\tif( cmd.equals(\"退出(X)\") )\t{\n" +
                "\t\t\tSystem.exit( 0 );\n" +
                "\t\t}\n" +
                "\t}\n" +
                "在此，我们同样可以使用3.4.1中的方法来消除这一堆的if…else，在这里不再详细描述。\n" +
                "3.4.3 打开图片\n" +
                "这个图片浏览器，打开一个图片文件之后，会把这个文件所有文件夹类的所有图片类型的的文件缓存起来，目的是为了不用每次都去搜索这个文件夹内的文件，也方面“上一张”和“下一张”的定位，缓存的文件都保存在本类的currentFiles中，currentFiles是一个List<File>类型。\n" +
                "代码清单：code\\viewer\\src\\org\\crazyit\\viewer\\ViewerService.java\n" +
                "public void open( ViewerFrame frame ) {\n" +
                "\t\t//如果选择打开\n" +
                "\t\tif( fileChooser.showOpenDialog( frame ) \n" +
                "\t\t\t== ViewerFileChooser.APPROVE_OPTION ) {\n" +
                "\t\t\t//给目前打开的文件赋值\n" +
                "\t\t\tthis.currentFile = fileChooser.getSelectedFile();\n" +
                "\t\t\t//获取文件路径\n" +
                "\t\t\tString name = this.currentFile.getPath();\n" +
                "\t\t\t//获取目前文件夹\n" +
                "\t\t\tFile cd = fileChooser.getCurrentDirectory();\n" +
                "\t\t\t//如果文件夹有改变\n" +
                "\t\t\tif( cd != this.currentDirectory \n" +
                "\t\t\t\t|| this.currentDirectory == null )\t{\n" +
                "\t\t\t\t//或者fileChooser的所有FileFilter\n" +
                "\t\t\t\tFileFilter[] fileFilters = fileChooser\n" +
                "\t\t\t\t\t.getChoosableFileFilters();\n" +
                "\t\t\t\tFile files[] = cd.listFiles();\n" +
                "\t\t\t\tthis.currentFiles = new ArrayList<File>();\n" +
                "\t\t\t\tfor( File file : files ) {\n" +
                "\t\t\t\t\tfor( FileFilter filter : fileFilters ) {\n" +
                "\t\t\t\t\t\t//如果是图片文件\n" +
                "\t\t\t\t\t\tif( filter.accept( file ) ) {\n" +
                "\t\t\t\t\t\t\t//把文件加到currentFiles中\n" +
                "\t\t\t\t\t\t\tthis.currentFiles.add( file );\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t\tImageIcon icon = new ImageIcon( name );\n" +
                "\t\t\tframe.getLabel().setIcon( icon );\n" +
                "\t\t}\n" +
                "\t}\n" +
                "首先用ViewerFileChooser对象的showOpenDialog方法弹出一个文件选择框，在用户未选择图片之前，做其它操作的时候，这里就获取当前的文件路径与当前的文件夹。\n" +
                "如果currentDirectory（当前文件夹）为空（证明是第一次打开文件）或者是currentDirectory不等于现在打开的文件夹，那么证明文件夹的路径有改变，就读取这个文件夹下面的所有文件。\n" +
                "在读取文件的过程中，先调用ViewerFileFilter中的getChoosableFileFilters()方法获取我们自定义的文件过滤器，如果读取到的文件类型属于当前的文件过滤器中允许的类型，就把这个文件加到currentFiles中缓存起来。\n" +
                "最后，用当前选择到的文件为参数新建一个ImageIcon对象，并调用ViewerFrame对象中JLabel对象的setIcon方法，把图片设置进去，就完成了显示图片的过程。\n" +
                "3.4.4 放大或者缩小图片\n" +
                "Image中有一个叫getScaledInstance的方法，能根据宽度去按比例改变图片的大小。在这个缩放方法（zoom）中，用参数isEnlarge是代表放大或者缩小的。如果isEnlarge等于true，就代表是放大，反之是缩小。\n" +
                "代码清单：code\\viewer\\src\\org\\crazyit\\viewer\\ViewerService.java\n" +
                "public void zoom( ViewerFrame frame, boolean isEnlarge ) {\n" +
                "\t\t//获取放大或者缩小的乘比\n" +
                "\t\tdouble enLargeRange = isEnlarge ? 1 + range : 1 - range;\n" +
                "\t\t//获取目前的图片\n" +
                "\t\tImageIcon icon = (ImageIcon)frame.getLabel().getIcon();\n" +
                "\t\tif( icon != null ) {\n" +
                "\t\t\tint width = (int)(icon.getIconWidth() * enLargeRange);\n" +
                "\t\t\t//获取改变大小后的图片\n" +
                "\t\t\tImageIcon newIcon = new ImageIcon( icon.getImage()\n" +
                "\t\t\t\t.getScaledInstance( width,-1,Image.SCALE_DEFAULT) );\n" +
                "\t\t\t//改变显示的图片\n" +
                "\t\t\tframe.getLabel().setIcon( newIcon );\n" +
                "\t\t}\n" +
                "\t}\n" +
                "首先是通过isEnlarge去得到缩放的比例（放大是大于1，缩小是0与1之间），接下来从Jlable中用getIcon方法获的ImageIcon图片对象，如果这个对象不为空，就从这个对象中调用getIconWidth方法得到宽度，并用这个宽度和缩放比例相乘得到新的宽度。\n" +
                "用新的宽度为参数去调用getScaledInstance方法得到新的ImageIcon对象，最后又调用JLabel的setIcon方法把这图片设置到JLabel对象中去。\n" +
                "3.4.5 “上一张”、“下一张”图片\n" +
                "前面知道，ViewerService中保存着当前打开的文件currenFile，还有这个文件夹下面的所有图片文件currentFiles，那么，读取“上一张”或者“下一张”图片就变的简单了，只要得到一个图片的索引，就能从currentFiles中取到图片。\n" +
                "这里是以读取上一张图片的方法为例子说明，读取下一张图片的实现是类似的。\n" +
                "代码清单：code\\viewer\\src\\org\\crazyit\\viewer\\ViewerService.java\n" +
                "public void last( ViewerFrame frame ) {\n" +
                "\t\t//如果有打开包含图片的文件夹\n" +
                "\t\tif( this.currentFiles != null && !this.currentFiles.isEmpty() )\t{\n" +
                "\t\t\tint index = this.currentFiles\n" +
                "\t\t\t\t.indexOf( this.currentFile ) ;\n" +
                "\t\t\t//打开上一个\n" +
                "\t\t\tif( index  > 0 ) {\n" +
                "\t\t\t\tFile file = (File)this.currentFiles.get( index - 1);\n" +
                "\t\t\t\tImageIcon icon = new ImageIcon( file.getPath() );\n" +
                "\t\t\t\tframe.getLabel().setIcon( icon );\n" +
                "\t\t\t\tthis.currentFile = file;\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "如果currentFile与currentFiles都不为空（证明当前是有打开文件的），那就用currentFile从currentFiles中得到当前文件的索引，并把这个索引减1，去获取上一个文件。\n" +
                "获取到上一个文件后，调用File类的getPath()方法得到文件的路径，然后以这个为参数来创建一个ImageIcon，最后把它设置到JLabel中。\n" +
                "3.5 文件选择与过滤\n" +
                "使用JFileChooser创建文件对话框流程是先使用构造器创建一个JFileChooser对象，然后调用JFileChooser对象的showXXXDialog的方法显示文件对话框，如果需要对文件进行过滤，就需要调用addChoosableFileFilter(FileFilter filter)方法添加文件过滤器，见以下代码。\n" +
                "代码清单：code\\viewer\\src\\org\\crazyit\\viewer\\ViewerFileChooser.java\n" +
                "private void addFilter() {\n" +
                "\t\tthis.addChoosableFileFilter( new MyFileFilter( \n" +
                "\t\t\tnew String[]{\".BMP\"}, \"BMP (*.BMP)\" ) );\n" +
                "\t\tthis.addChoosableFileFilter( new MyFileFilter( \n" +
                "\t\t\tnew String[]{\".JPG\",\".JPEG\",\".JPE\",\".JFIF\"}, \n" +
                "\t\t\t\"JPEG (*.JPG;*.JPEG;*.JPE;*.JFIF)\") );\n" +
                "\t\tthis.addChoosableFileFilter( new MyFileFilter( \n" +
                "\t\t\tnew String[]{\".GIF\"}, \"GIF (*.GIF)\" ) );\n" +
                "\t\tthis.addChoosableFileFilter( new MyFileFilter( \n" +
                "\t\t\tnew String[]{\".TIF\",\".TIFF\"}, \"TIFF (*.TIF;*.TIFF)\" ) );\n" +
                "\t\tthis.addChoosableFileFilter( new MyFileFilter( \n" +
                "\t\t\tnew String[]{\".PNG\"}, \"PNG (*.PNG)\" ) );\n" +
                "\t\tthis.addChoosableFileFilter( new MyFileFilter( \n" +
                "\t\t\tnew String[]{\".ICO\"}, \"ICO (*.ICO)\" ) );\n" +
                "\t\tthis.addChoosableFileFilter( new MyFileFilter( \n" +
                "\t\t\tnew String[]{\".BMP\",\".JPG\",\".JPEG\",\".JPE\",\".JFIF\",\n" +
                "\t\t\t\".GIF\",\".TIF\",\".TIFF\",\".PNG\",\".ICO\"}, \n" +
                "\t\t\t\"所有图形文件\") );\n" +
                "\t}\n" +
                "这里是把bmp，jpg，gif等类型的文件过滤器都加到JFileChooser中，留意到这里是调用MyFileFilter( String[] suffarr,String decription )这个构造器去创建一个FileFiler，第一个参数是后缀名，第二个参数是描述，见以下代码。\n" +
                "代码清单：code\\viewer\\src\\org\\crazyit\\viewer\\ViewerFileChooser.java\n" +
                "public MyFileFilter( String[] suffarr,String decription ) {\n" +
                "\t\tsuper();\n" +
                "\t\tthis.suffarr = suffarr;\n" +
                "\t\tthis.decription = decription;\n" +
                "\t}\n" +
                "MyFileFilter继承了FileFilter，我们这里重写它的accept方法，去定义过滤的规则，见以下代码。\n" +
                "代码清单：code\\viewer\\src\\org\\crazyit\\viewer\\ViewerFileChooser.java\n" +
                "public boolean accept( File f ) {\n" +
                "\t\t//如果文件的后缀名合法，返回true\n" +
                "\t\tfor ( String s : suffarr ) {\n" +
                "\t\t\tif ( f.getName().toUpperCase().endsWith( s ) ) {\n" +
                "\t\t\t\treturn true;\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t\t//如果是目录，返回true,或者返回false\n" +
                "\t\treturn f.isDirectory();\n" +
                "\t}\n" +
                "到此，我们整个图片浏览器已经实现，本章所涉及的内容较少，到现在可能运行程序查看具体的效果。\n" +
                "3.6 本章小结\n" +
                "本章通过图片浏览器的基本实现，向读者介绍了JFileChooser文件选择框与FileFilter的用法，使用了AbstractAction去创建按钮，并响应按钮事件。在代码中，使用List缓存的技巧简单去实现了“上一张”、“下一张”图片的功能，并让读者体会到可以怎样子去操作一张图片，例如改变图片的大小等操作。在本章实现工具栏点击时，我们使用了Java的反射来创建具体具体的某个工具栏Action类，我们在本章中初步使用了Java的反射，在以后的章节中，我们会更多的使用各种Java技术，目的是为了减低程序的耦合，编写更多可扩展的程序。')");
        db.execSQL("insert into book_chapter(book_english_name,chapter_name,chapter_id,content)values('crazy_java','第4章 桌面弹球','4','4.1 桌面弹球概述\n" +
                "桌面弹球是游戏中常见的游戏，从以前的掌上游戏机到如今的手机游戏，都是一个十分经典的游戏。玩家控制一个可以左右移动的挡板去改变运动中小球的移动方向，目的是用小球消除游戏屏幕中的所有障碍物到达下一关，在障碍物被消除的过程中，可能会产生一些能改变挡板或者小球状态的物品，例如：挡板变长、变短，小球威力加强等等。本章主要介绍如何实现一个简单的弹球游戏，让读者了解“动画”的实现原理。\n" +
                "在本章中，将介绍与使用Java的绘图功能，使用到JPanel的paint(Graphics g)方法去绘图，绘图主要是依靠这个方法中的Graphics类型的参数，将使用Java中的Timer去重复绘图，产生动画效果，桌面弹球游戏的效果如图4.1所示。\n" +
                "\n" +
                "图 4.1 桌面弹球\n" +
                "4.1.1 动画原理\n" +
                "简单地来说，动画是利用人的视觉暂留的生理特性，实现出来的一种假象，只要每隔一段时间（这个时间少于人的视频暂留时间）就重新绘制一幅状态改变的图片，就能造成这种“动”的假象。我们在程序中不断的进行绘画（使用repaint方法），对程序来讲，只需要在短时间内进行多次的绘画，并且每次绘画都需要改变绘画的相关值，就可以达到“动画”的效果。\n" +
                "4.1.2 小球反弹的方向\n" +
                "在本章实现的过程中，我们会设置小球于对称的方式，并出现少许偏移的方式反弹，如图4.2所示。让小球反弹出现少许编移是为了让游戏增加点不确定性，增加游戏的趣味性。我们需要在编写游戏前确定这些小细节，这样在开发的过程中，我们就可以按照这些小细节去逐步实现我们的程序。\n" +
                "\n" +
                "图 4.2 小球的反弹\n" +
                "4.2 流程描述\n" +
                "玩家使用左右方向键开始游戏与控制挡板，在未消除完所有的障碍物或者挡板没有档住向下移动的小球之前，会一直处于游戏状态，在这个状态中，小球会一直处于直线运动或者改变方向，当小球消除掉障碍物的时候，有机率产生一些物品，产生的物品会直线向下移动，用挡板接住物品后，物品的特殊效果会生效。如果消除了所有的障碍物，就判断玩家为赢，如果挡板没有接住向下移动的小球，就判断玩家为输。具体的游戏流程如图4.3所示。\n" +
                "\n" +
                "图 4.3 游戏流程\n" +
                "游戏中并不涉及复杂的流程，只需要处理游戏的输赢即可，因此在实现的过程中，关键是如何确定游戏输赢的标准（挡栏没有挡住小球）。\n" +
                "4.3 创建游戏对象\n" +
                "在这个游戏中，有挡板，小球，砖块（障碍物），道具等物品，这些物品都有共同的特性，有属于自己的x与y坐标属性，有图片属性，有速度属性，所以，在这时在，设计一个基类BallComponent包含这些属性与相关的方法，让其子类继承。继承此类的子类有Stick类（用于定义挡板的行为于属性），Ball类（控制小球的移动与其它动作），Brick类（砖块类），Magic类(道具抽像类，此类中有一个用于使道具功能实现的抽象方法，供其子类实现)。道具类的子类有LongMagic与ShortMagic，作用是使Stick的长度变长或者变短。在平时的开发中，如果发现多个对象之间有一些共同的特性或者行为，并且觉得可以使用这些特性或者行为构成一个对象，那么可以建立一个新的对象作为这些对象的父类。如果该父类中某些方法并不需要由父类实现，我们可以将父类做成抽象类，并将这些方法变成抽象的。\n" +
                "确定了我们游戏中的所涉及的对象后，我们还需要一个BallFrame类去创建一个画板，用于绘制图片，此类还完成界面的初始化，监听用户的键盘，而与游戏相关的业务逻辑（判断输赢或者球的运动），我们放到BallService类中去处理，本章类的关系如图4.4所示。\n" +
                "\n" +
                "图 4.4 桌面弹球类图\n" +
                "笔者在这里提供了本章的类图，是为了让读者可以更清晰的了解本章程序的结构，但在实现开发的过程中，我们可以根据实际情况，加入或者改变各个类的关系或者程序的结构，但最终都是为降低程序的耦合、提高内聚、编写出优秀的代码。\n" +
                "4.3.1 基类BallComponent\n" +
                "BallComponent，做为Brick（砖块）类、Magic（道具）类、Stick（挡板）类、Ball（小球）类的父类，定义了这些子类共有的属性与方法，属性有：x坐标，初始值为-1；y坐标，初始值为-1；图片image，初始值为null；速度speed，初始值为5。根据不同的需要，提供以下三个构造方法：\n" +
                "\uF071BallComponent( String path )，path是图片的路径，用图片的路径来构造一个BallComponent，在此构造方法中，将根据路径去读取图片，再设置对象中image属性。\n" +
                "\uF071BallComponent( int panelWidth , int panelHeight, String path )，以panelWidth，panelHeight，与path去构造一个Component。 \n" +
                "\uF071BallComponent( String path , int x , int y )，以x坐标，y坐标和path去构造一个BallComponet。\n" +
                "除去这些构造方法，此类提供了这些属性的setter与getter方法，用于获取对象的坐标与图片，或者改变对象的坐标位置与图片属性。如果我们在编码的过程中发现有一些共同的属性或者方法，我们可以将这些放到这个基类中。\n" +
                "创建BallComponent的时候，我们可以将这个类变成抽象类，即使它没有任何的抽象方法，这样做的目的是，在我们的桌面弹球游戏中，该类并不是具体存在的某一个对象，而是我们将一些公用的属性或者方法存放到该类中，因此它在游戏中并不代表某个具体的对象。将该类创建为抽象类，我们就可以提供（如果需要的话）一些抽象方法让子类去实现，并且可以在父类中调用这些抽象方法。\n" +
                "4.3.2 砖块类（Brick）\n" +
                "此类是BallComponet的一个子类，提供一个Brick(String path, int type, int x, int y )构造器，其中pah、x与y参数用于调用父类的构造器，type是代表砖块的类型：1代表此砖块里面有LongMagic类型 的道具；2代表此砖块里面有ShortMagic类型的道具；其它代表此砖块里面没有道具。另外，本类增加了magic与disable属性，magic代表此砖块中所包含的道具，初始值为null，disable是用来标志Brick的状态，如果diable为true，则表明此砖块已经不可用，不会再显示。并提供这两个属性相关的以下方法：\n" +
                "\uF071void setMagic( Magic magic )，设置道具。\n" +
                "\uF071Magic getMagic()，获取道具。\n" +
                "\uF071boolean isDisable()，用来判断此类是否有效。\n" +
                "\uF071void setDisable( boolean disable )，停用或者启用此类，disable的值为true或者false。\n" +
                "确定了一个砖块由一个Brick对象来表示后，在界面中，我们可以提供一个Brick的二维数组，来表示界面中所有的砖块，实现原理与控制台五子棋中的棋盘一样，但是在本章中，二维数组的每一个元素并不是字符串，而是具体的某个Brick对象，在以后的章节中，当遇到需要在界面中绘画某些图片的时候，我们都可以建立一个二维数组，将相应的对象放置到该数组中，当界面进行绘画的时候，就可以将这个二维数组“画”出来。\n" +
                "4.3.3 道具类及其子类（Magic）\n" +
                "Magic类是一个道具类，在游戏中表现包含在砖块中，是BallComponet的一个抽象子类，此类提供一个Magic( String path, int x , int y )构造器去调用父类的构造器，并提供一个抽象的方法magicDo( Stick stick )，此抽象方法是实现道具的效果功能，用于给其子类实现，现在实现的子类的LongMagic类和ShortMagic类，两个子类的magicDo方法中分别实现使挡板变长与变短的功能。\n" +
                "\uF071abstract void magicDo( Stick stick )，道具的功能，给其子类实现。\n" +
                "在本例中，挡板是可以变长或者变短的，而使挡板变长或者变短的方式是通过道具来实现，因此可以将道具抽象成变长的道具或者变短的道具，而它们都需要做同一件是，就是改变挡板的展现形式。为了程序的可扩展性，我们在这里将一个道具变为一个抽象类（Magic），当我们需要有其他形式的道具的时候，就可以为该类添加子类，并提供不同的实现。当然，这里只提供一个Stick的参数可能并不够，如果以后游戏中出现另外一种道具，会改变球的速度（变快或者变慢），那么我们就需要为该抽象类提供更多的参数。\n" +
                "4.3.4 挡板类（Stick）\n" +
                "同样，Stick 类也是BallComponet的子类，用来代表游戏中的挡板，由于挡板只有左右移动的，所以，此类中只定义了挡板x方向的移动速度SPEED ，还有定义挡板的初始长度preWidth ，并提供此方法的setter与getter方法，如下： \n" +
                "\uF071void setPreWidth( int preWidth )，设置初始长度。\n" +
                "\uF071int getPreWidth()，获取初始长度。\n" +
                "由于该类继承于BallComponet类，因此只需要提供一个构造器即可。在本例中，挡板是可以变长或者变短的，并且在建立道具抽象类的时候，已经定义了一个magicDo的方法，该方法的参数就是一个挡板对象，所以挡板类必须包括长度的属性，这样，在实现道具类的时候，就可以通过改变挡板类的长度来实现本例中所需要实现的长短挡板功能。在Stick类中并不需要关心挡板的图片、位置与大小，这些属性已经在BallComponet中体现。\n" +
                "4.3.5 小球类（Ball）\n" +
                "Ball类也是BallComponet的子类，由于小球在游戏面板中运动的时候除了横竖方向，还有各种角度的斜方向，所以我们把小球的速度分解成横向速度与竖向速度（speedX与speedY），游戏未开始前，小球是处于静止状态，所以用一个started属性来标志小球是否已经开始运动。游戏结束后，小球也是处于静止状态，但不能再移动，同样，用一个stop属性来标志小球是否能再移动。除了定义这些属性，还为这些属性提供相应的setter与getter 方法，如下： \n" +
                "\uF071setSpeedX( int speed )，设置小球的横向速度。\n" +
                "\uF071setSpeedY( int speed )，设置小球的竖向速度。\n" +
                "\uF071boolean isStarted()，小球是否已经在运动。\n" +
                "\uF071void setStarted( boolean b )，把小球状态设置为运动或者静止。\n" +
                "\uF071int getSpeedX()，获取小球的横向速度。\n" +
                "\uF071int getSpeedY()，获取小球的竖向速度。\n" +
                "在本例中，小球对象只保存一些相关的属性，例如横向速度与纵向速度（图片、位置与大小在父类中体现），如果需要改变小球的速度，可以调用相关的setter方法来进行，但是我们需要知道由哪些对象来改变小球的相关属性，我们在前面的章节中提到，提供一个业务类进负责处理游戏的相关逻辑，因此，业务类就需要维护一个小球的对象，来控制小球的运动或者其他行为。在这里，小球对象可以单纯的看作一个简单的对象，并不负责处理任何的行为，这可以看作我们一般所说的贫血模式，对象并不负责处理任何的业务逻辑。如果需要将该小球对象编写成为充血模式，可以为小球对象提供一些与之相关的行为，例如小球会运动，我们可以为Ball类加入一个run的方法，表示球的运动，例如小球会停止运动（在游戏结束或者开始时），我们就可以为Ball类添加一个stopRun的方法，总之，如果需要做到充血模式，可以将所有与小球相关的方法加入到Ball中。\n" +
                "4.3.6 业务处理类（BallService）\n" +
                "BallService 处理了这个游戏中的大部分业务功能，包括开始游戏、小球移动、道具移动、挡板移动、测试小球与挡板是否有碰撞或者挡板和其它元素有碰撞、设置挡板的长度、判断用户是否通关、初始化砖块的排列与道具、画图等功能。这些功能的实现都有对应的方法，如下： \n" +
                "\uF071void run()，小球进行运动。\n" +
                "\uF071void setStickPos( KeyEvent ke )，改变挡板的坐标位置。\n" +
                "\uF071setBallPos()，改变小球的坐标位置。\n" +
                "\uF071boolean isHitBrick( Brick brick )，测试小球与砖块是否有碰撞，参数brikc是指砖块。\n" +
                "\uF071isHitStick( BallComponent image )，测试某元素与挡板是否有碰撞。\n" +
                "\uF071void setMagicPos()，改变道具的坐标位置。\n" +
                "\uF071void setStickWidth( Magic magic )，根据道具（magic）的类型去设置改变挡板的长度。\n" +
                "\uF071boolean isWon()，判断玩家是否已经过关。\n" +
                "\uF071Brick[][] createBrickArr( String path, int xSize, int ySize )，创建砖块，返回一个Brick类型的数组，参数path是指砖块的图片，xSize与ySize是数组的长度。\n" +
                "\uF071void draw( Graphics g )，画图，方法中是使用Graphics对象g去画图。\n" +
                "当游戏开始时，程序中需要不停的调用run方法，让小球进行运动，当然，小球进行运动的前提是Ball的isStarted方法返回true，即游戏已经开始，run方法的主要功能就是调小球的位置。我们需要在游戏中通过上、下、左、右的键来控制挡板的位置，因此就需要提供一个setStickPos的方法来改变挡板的位置。在本章的程序中，BallService处理所有的相关逻辑，例如判断小球在运动的过程中是否越界、游戏是否胜利等。在例中BallService处理了大部分的游戏逻辑，当然，我们也可将这些逻辑放到相关的类中（即前面提到的充血模式），例如道具的下落、挡板的移动等。\n" +
                "4.3.7 主界面类（BallFrame）\n" +
                "BallFrame是创建一个JFrame主界面，设置主界面的标题、长与宽、画板等属性，并且为增加键盘事件监听器以及创立一个Timer每隔一小段时间去刷新画板，主要有初始化界面与或者画板两个方法，如下： \n" +
                "\uF071void initialize() throws IOException，此方法抛出IO异常，初始化界面。\n" +
                "\uF071BallPanel getBallPanel()，获取一个BallPanel类型的JPanel去充当画板，BallPanel是这个类中的一个内部类。\n" +
                "我们使用了BallService类来处理大部分的游戏逻辑，主界面类中几乎不包括任何的逻辑处理，该类维护一个BallService的对象，得到界面中相关对象的信息后，可以调用BallService中的方法进行处理，并根据返回的信息来改变界面。例如小球的运动，我们可以调用BallService的run方法，再调用BallSerivce的draw方法将小球的图片“画”到界面中。\n" +
                "到此，本章中所有的对象都已经创建并确定了它们的行为，在建立道具类（Magic）的时候，我们将一个道具抽象为一个Magic对象，该类可以有多个实现，在使用Magic对象的时候，我们可以利用面向对象的多态特性，使用Magic的magicDo方法来进行“道具的使用”，在这个过程中，我们并不需要去关心道具具体的实现。在创建游戏各个对象的过程中，我们将处理逻辑的方法放置到一个业务类中，从一定程度上讲，减少了代码之间的耦合，并遵循了单一职责的原则。\n" +
                "4.4 主界面实现\n" +
                "在这个桌面弹球游戏中，游戏中的所有元素都是用Graphics对象画出来的，所以，我们的主界面应该是一个只设置了窗口标题还有颜色等基本属性的JFrame，在这个JFrame中，我们只需要提供一个JPanel对象即可，因为游戏的界面并没有多复杂的布局与界面交互。当我们实现游戏的一些相关逻辑的时候（球的运动、道具的下落等），我们可以调用JPanel的repaint方法将JPanel进行重绘。\n" +
                "4.4.1 初始化界面（initialize()方法）\n" +
                "首先，设置JFrame窗口的标题、背景颜色与是否可以改变大小，然后获取JPanel对象，最后把JPanel画板加到JFrame中，见以下代码。\n" +
                "代码清单：code\\ball\\src\\org\\crazyit\\ball\\BallFrame.java\n" +
                "public void initialize()  throws IOException {\n" +
                "\t//设置窗口的标题\n" +
                "\tthis.setTitle(\"弹球\");\n" +
                "\t//设置为不可改变大小\n" +
                "\tthis.setResizable( false );\n" +
                "\t//设置背景为黑色\n" +
                "\tthis.setBackground( Color.BLACK );\n" +
                "\t//获取画板\n" +
                "\tballPanel = getBallPanel();\n" +
                "\t//把画板加到JFrame\n" +
                "\tthis.add( ballPanel );\n" +
                "}\n" +
                "看加粗的一行代码ballPanel = getBallPanel()是调用本类中的getBallPanel()方法去获取一个BallPanle对象，BallPanel是本类的一个内部类，并且继承JPanel，见以下代码。\n" +
                "代码清单：code\\ball\\src\\org\\crazyit\\ball\\BallFrame.java\n" +
                "//定义一个JPanel内部类来完成画图功能\n" +
                "public class BallPanel extends JPanel {\n" +
                "\t/**\n" +
                "\t * 重写void paint( Graphics g )方法\n" +
                "\t *\n" +
                "\t * @param g Graphics\n" +
                "\t * @return void\n" +
                "\t */\n" +
                "\tpublic void paint( Graphics g ) {\n" +
                "\t\t//可以调用BallService的draw方法进行绘制\n" +
                "\t}\n" +
                "}\n" +
                "而获取这个BallPanel实现是在BallPanel getBallPanel方法中，此类保证这个Panel是单态的，每次只有一个BallPanle对象，见以下代码。\n" +
                "代码清单：code\\ball\\src\\org\\crazyit\\ball\\BallFrame.java\n" +
                "public BallPanel getBallPanel() {\n" +
                "\tif ( ballPanel == null ) {\n" +
                "\t\t//新建一个画板\n" +
                "\t\tballPanel = new BallPanel();\n" +
                "\t\t//设置画板的大小\n" +
                "\t\tballPanel.setPreferredSize( \n" +
                "\t\t\tnew Dimension( BALLPANEL_WIDTH, BALLPANEL_HEIGHT ) );\n" +
                "\t}\n" +
                "\treturn ballPanel;\n" +
                "}\n" +
                "在这里需要注意的是，我们需要在BallFrame中维护一个BallPanel的对象，然后通过getBallPanel的方法来获得BallPanel的实例，由于BallPanel并不需要每次去创建，所以我们可以将BallPane变成单态的。在众多的设计模式中，有一种叫做单态模式。如果遇到一些对象并不需要多次创建或者创建这些对象将会严重消耗系统资源，那么我们可以考虑将该对象写成单态的。\n" +
                "4.4.2 单态模式简介\n" +
                "单态模式也可以叫单例模式，该模式保证一个类有且仅有一个实例，并为外界提供一个访问，让外界可以通过这个访问点来访问该类的唯一实例。在我们平时开发的过程中，会遇到一些不需要多次创建的对象，例如JDBC的Connection对象，那么我们就可以利用单态模式来创建这些对象。例如单态模式，系统可以不必多次创建该对象的实例，外界使用的时候可以使用同一个实例，因此在一定程序上减低了系统在创建对象时的开销。\n" +
                "为一个类实现单态模式，需要为该类提供一个私有的构造器，再提供一个可以获取该类实现的方法（为外界提供唯一的访问点），私有构造器是为了不让外界去使用new关键字来创建该类的实现，如果外键可以使用new关键字来创建该类的实例，那么就意味着该类将不会是单态，有可能外界多次通过new关键字来创建，这就无法保证该对象的实例的唯一性。\n" +
                "4.4.3 运行效果\n" +
                "编写了BallFrame的初始化代码后，我们可以运行具体查看相关的游戏效果。编写创建BallFrame的代码：\n" +
                "\t\tBallFrame ballFrame = new BallFrame();\n" +
                "\t\tballFrame.pack();\n" +
                "\t\tballFrame.setVisible(true);\n" +
                "\t\tballFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n" +
                "当前程序的效果如图4.5所示。\n" +
                "\n" +
                "图4.5 初始化游戏时的界面\n" +
                "注：我们当前并没有对BallService中的draw方法作任何的实现，我们实现了BallService的draw方法后，就可以将BallPanel中的paint方法加入BallService.draw。\n" +
                "4.4.4 监听器与Timer\n" +
                "javax.swing.Timer可以设定每隔一个时间周期就重复执行某个task，类似于Window系统的计划任务或者Linux系统的crobtab，并用start()方法去启用Timer。在这个弹球游戏中，我们只有键盘操作，所以只监听键盘的操作，用一个KeyListener去监听键盘的动作，请看以下代码。\n" +
                "代码清单：code\\ball\\src\\org\\crazyit\\ball\\BallFrame.java\n" +
                "//定义每0.1秒执行一次监听器\n" +
                "ActionListener task = new ActionListener(){\n" +
                "\tpublic void actionPerformed( ActionEvent e ) {\n" +
                "\t\t//开始改变位置\n" +
                "\t\tservice.run();\n" +
                "\t\t//刷新画板\n" +
                "\t\tballPanel.repaint();\n" +
                "\t}\n" +
                "};\n" +
                "//如果timer不为空，调用timer的restart方法\n" +
                "if( timer != null ) {\n" +
                "\t//重新开始timer\n" +
                "\ttimer.restart();\n" +
                "} else {\n" +
                "\t//新建一个timer\n" +
                "\ttimer = new Timer( 100, task );\n" +
                "\t//开始timer\n" +
                "\ttimer.start();\n" +
                "}\n" +
                "//增加事件监听器\n" +
                "KeyListener[] klarr = this.getKeyListeners();\n" +
                "if( klarr.length == 0 ) {\n" +
                "\t//定义键盘监听适配器\n" +
                "\tKeyListener keyAdapter = new KeyAdapter(){\n" +
                "\t\tpublic void keyPressed( KeyEvent ke ) {\n" +
                "\t\t\t//改变挡板的坐标\n" +
                "\t\t\tservice.setStickPos( ke );\n" +
                "\t\t}\n" +
                "\t};\t\t\t\n" +
                "\tthis.addKeyListener( keyAdapter );\n" +
                "}\n" +
                "首先，建立一个ActionListener对象做为Timer的task，这个task主要是处理游戏中各个组件位置的改变以及reapint画板，这个task每100毫秒执行一次，即每隔一百毫秒小球（或者其他组件）会执行一次运动。如果此类的属性timer为空，就以ActionListern对象为参数去创建一个每100毫秒执行一次的Timer，并用调用start()方法启动Timer，如果timer不为空，直接调用restart()方法启动timer。在这里我们需要明白的是，第一次进行游戏时，timer为null，就需要进行创建，当进行第二次游戏的时候，timer非空，由于游戏停止（胜利或者失败），因此需要调用restart方法重新启动。由于我们是在BallService控制游戏的，也就意味着进行第二次游戏的时候，就需要再次调用BallFrame的initialize方法初始化游戏。\n" +
                "接下来再增加事件监听器，先使用JFrame的keyKeyListeners()方法获取本窗口的KeyLister数组，如果这个数组的长度为空，说明本窗口并没有添加到任何KeyListener，所以就创建一个KeyAdapter（为JFrame创建一个键盘监听器）并重写KeyAdapter类的void keyPressed(KeyEvent ke)方法，这个方法用来监听键盘的按键是否有按下，如果有的话，就需要调用BallService的setStickPos方法。当我们去实现setStickPos方法的时候，就需要设置小球为运动状态，启动弹球游戏就意味着小球开始进行运动。当我们在游戏中按下左右键的时候，同时需要移动挡板，启动游戏后，我们并不需要关心小球的移动，仅仅设置小球的运动状态，换言之，setStickPos方法只是处理挡板的移动，小球的运动让BallService的run处理（run方法100毫秒执行一次）。\n" +
                "4.5 挡板、小球、砖块、道具\n" +
                "在这个设计中，挡板、小球、砖块与砖块中所包含的道具都有一个共同的父类BallComponet，可以使用父类的setX与setY方法设置坐标，也可以使用getX与getY方法获取坐标，还可以使用getImage方法获取图片，并且父类根据不同的情况提供了几个不同的构造器，\n" +
                "4.5.1 挡板（Stick类）\n" +
                "此类提供一个以画板的宽、高和挡板的图片路径为参数的构造器，见以下代码。\n" +
                "代码清单：code\\ball\\src\\org\\crazyit\\ball\\Stick.java\n" +
                "public Stick( int panelWidth , int panelHeight, String path )  throws IOException {\n" +
                "\t//调用父构造器\n" +
                "\tsuper( panelWidth, panelHeight, path );\n" +
                "     //设置y坐标\n" +
                "\tthis.setY( panelHeight - super.getImage().getHeight( null ) );\n" +
                "\t//设置原本的长度\n" +
                "\tthis.preWidth = super.getImage().getWidth( null );\n" +
                "}\n" +
                "首先调用父类的BallComponent(int x, int y, String path)构造器，把此对象的x坐标设置到画板中间的位置，并且使用javax.imageio.ImageIO的read方法去读取磁盘中的图片文件。接下来把y坐标设置到画板的底部，再根据读取出来的图片的宽度去设置Stick对象的初始长度属性。在从磁盘读取图片的过程是一个IO操作，所以会抛出IOException，见以下代码。\n" +
                "代码清单：code\\ball\\src\\org\\crazyit\\ball\\BallComponent.java\n" +
                "public BallComponent( int panelWidth , int panelHeight, String path ) throws IOException {\n" +
                "\tsuper();\n" +
                "\t//读取图片\n" +
                "\tthis.image = ImageIO.read( new File( path ) );\n" +
                "\t//设置x坐标\n" +
                "\tthis.x = (int)( ( panelWidth - image.getWidth( null ) ) / 2 );\n" +
                "}\n" +
                "由于挡板的长度可能会改变，所以Stick类有的个int 类型的preWidth属性，代表挡板的长度，并定义一个final int类型的SPEED属性，代表挡板的移动速度，每次移动，x坐标都会向左或者向右移动SPEED个坐标位置，需要为preWidth属性提供setter与getter方法。\n" +
                "实现了挡板类后，我们可以实现BallService的draw方法，先将挡板“画”到BallPanel中，并在BallPanel中调用BallService的draw方法，以下是BallService的draw方法的部分实现。\n" +
                "代码清单：code\\ball\\src\\org\\crazyit\\ball\\BallService.java\n" +
                "\t\t// 如果赢了\n" +
                "\t\tif (isWon()) {\n" +
                "\t\t\t// 绘制赢的图片\n" +
                "\t\t\tg.drawImage(won.getImage(), won.getX(), won.getY(), width,\n" +
                "\t\t\t\t\theight - 10, null);\n" +
                "\t\t} else if (ball.isStop()) {\n" +
                "\t\t\t// 绘制游戏结束图像\n" +
                "\t\t\tg.drawImage(gameOver.getImage(), gameOver.getX(), gameOver.getY(),\n" +
                "\t\t\t\t\twidth, height - 10, null);\n" +
                "\t\t} else {\n" +
                "\t\t\t// 清除原来的图像\n" +
                "\t\t\tg.clearRect(0, 0, width, height);\n" +
                "\t\t\t// 绘制挡板图像\n" +
                "\t\t\tg.drawImage(stick.getImage(), stick.getX(), stick.getY(), stick\n" +
                "\t\t\t\t\t.getPreWidth(), stick.getImage().getHeight(null), null);\n" +
                "\t\t}\n" +
                "到此，我们可以运行程序查看创建挡板后的效果，具体的效果如图4.6所示。\n" +
                "\n" +
                "图4.6 创建挡板\n" +
                "4.5.2 小球（Ball类）\n" +
                "此类提供一个以画板的宽、高、挡板高度与小球的图片路径为参数的构造器，见以下代码。\n" +
                "代码清单：code\\ball\\src\\org\\crazyit\\ball\\Ball.java\n" +
                "public Ball( int panelWidth , int panelHeight , int offset, String path )\n" +
                "\t\tthrows IOException {\n" +
                "\t//调用父构造器\n" +
                "\tsuper( panelWidth, panelHeight, path );\n" +
                "\t//设置y坐标\n" +
                "\tthis.setY( panelHeight  - super.getImage().getHeight( null ) - offset  );\n" +
                "}\n" +
                "首先调用父类的BallComponent(int x, int y, String path)构造器，把此对象的x坐标设置到画板中间的位置，并且使用javax.imageio.ImageIO的read方法去读取磁盘中的图片文件。接下来把y坐标设置到板位上面的位置。\n" +
                "在这里，小球对象有两种状态，一种是小球是否开始运动，这种状态下，如果小球没有开始运动，代表准备开始游戏，反则代表游戏已经开始，没游戏没结束之前，小球就一直运动；一个是小球是否结束运动，如果小球结束运动，代表游戏已经结束，小球不能再运动，挡板也不再受玩家的控制，反则代表正在游戏中。我们在Ball中提供一个started的属性来标识这两种状态。那么当游戏开始时，就可以直接设置Ball的started属性为true。\n" +
                "我们把小球的速度方向分为横与竖两个方向，所以这里用int类型的speedX与speedY两个属性去代表小球的横向方向与竖向方向，并增加相应的setter与getter方法。为Ball对象添加了相关的属性后，我们可以在BallService的draw方法中，将一个小球“画”到BallPanel中。具体的效果如图4.7所示。\n" +
                "g.drawImage(ball.getImage(), ball.getX(), ball.getY(), null);\n" +
                "\n" +
                "图4.7 画小球图片\n" +
                "4.5.3 道具（Magic及其子类）\n" +
                "Magic类是一个抽像类，它是BallComponet的子类，又是LongMagic与ShortMagic的父类，此类只有一个抽象方法magicDo，用来完成道具的功能，提供一个使用图片路径与x、y坐标为参数的构造器供其子类继承，见以下代码。\n" +
                "代码清单：code\\ball\\src\\org\\crazyit\\ball\\Magic.java\n" +
                "public Magic( String path, int x , int y )  throws IOException {\n" +
                "\tsuper( path, x, y );\n" +
                "} \n" +
                "public abstract void magicDo( Stick stick );\n" +
                "这个构造器只调用父类BallComponent的构造器，去设置道具的表现图片与初始坐标。加粗的一行代码是用来完成道具功能的抽像方法，这里只有定义，没有实现，让其子类去实现。Magic类有两个子类：LongMagic与ShortMagic，这两个道具的功能是使游戏中的挡板变长和变短，功能都在magicDo的实现方法中实现，首先看LongMagic类实现的magicDo方法。\n" +
                "代码清单：code\\ball\\src\\org\\crazyit\\ball\\LongMagic.java\n" +
                "public void magicDo( Stick stick ) {\n" +
                "\tdouble imageWidth = stick.getImage().getWidth(null);\n" +
                "\t//如果挡板没有变长过\n" +
                "\tif( stick.getPreWidth() <= imageWidth ) \t{\n" +
                "\t\t//将挡板的长度改为双倍\n" +
                "\t\tstick.setPreWidth( (int)(stick.getPreWidth() * 2 ) );\n" +
                "\t}\n" +
                "}\n" +
                "首先获取挡板图片的长度，再拿这个长度和挡板现在的长度比较，如果挡板的长度小于或者等于图片的长度，说明挡板的长度没有增加过，所以就调用Stick的setPreWidth方法把挡板的长度设置为又倍，下面再看ShortMagic实现的magicDo方法。\n" +
                "代码清单：code\\ball\\src\\org\\crazyit\\ball\\ShortMagic.java\n" +
                "public void magicDo( Stick stick ) {\n" +
                "\tdouble imageWidth = stick.getImage().getWidth(null);\n" +
                "\t//如果挡板没有变短过\n" +
                "\tif( stick.getPreWidth() >= imageWidth ) {\n" +
                "\t\t//将挡板的宽度改为一半\n" +
                "\t\tstick.setPreWidth( (int)(stick.getPreWidth() * 0.5 ) );\n" +
                "\t}\n" +
                "}\n" +
                "这里的流程和LongMagic中实现的方法相似，首先获取挡板图片的长度，如果现在的长度大于或者等于图片的长度，说明挡板的长度没有减少过，就调用Stick的setPreWidth方法把挡板的长度设置为一半。\n" +
                "4.5.4 砖块（Brick类）\n" +
                "Brick类是BallComponet的一个子类，用一个boolean类型的属性disalbe去标志对象是否有效果，还包含一个Magic类型的属性magic，在构造器中初始化这个属性，见以下代码中。\n" +
                "代码清单：code\\ball\\src\\org\\crazyit\\ball\\Brick.java\n" +
                "public Brick(String path, int type, int x, int y ) throws IOException {\n" +
                "\tsuper(path);\n" +
                "\tif( type == Brick.MAGIC_LONG_TYPE ) {\n" +
                "\t\tthis.magic = new LongMagic( \"img/long.gif\", x, y ); \t\t\t\n" +
                "\t} else if( type == Brick.MAGIC_SHORT_TYPE ) {\n" +
                "\t\tthis.magic = new ShortMagic( \"img/short.gif\", x, y );\n" +
                "\t}\n" +
                "\tif( this.magic != null ) {\n" +
                "\t\tthis.magic.setX( x );\n" +
                "\t\tthis.magic.setY( y );\n" +
                "\t}\n" +
                "}\n" +
                "在这个构造器的参数中，除了读取图片文件的path参数和对象坐标的x与y参数，还有一个int类型的参数type，构造器主要是根据这个参数的值去决定此对象包含的Magic，如是type等于Brick.MAGIC_LONG_TYPE，magic就是一个LongMaigc对象，如果type等于Brick.MAGIC_SHORT_TYPE，magic就是一个ShortMagic对象，如果magic不是空值 ，就设置magic的x与y坐标。当然，同样需要为magic与disalbe属性增加相应的setter和getter方法。\n" +
                "4.6 BallService类实现\n" +
                "BallService被定义成一个专门处理此游戏逻辑功能的类，包含处理小球的移动、处理挡板的移动、初始化砖块与道具、判断玩家的输赢，判断游戏中的图片元素是否有碰撞，把图片绘制到画板等功能。由于BallService负责处理几乎全部的游戏逻辑，那么该类中就需要维护界面所有的组件：小球对象、挡板对象、砖块的二维数组等。BallService中所有的方法都是对这些对象进行处理，修改它们的相关属性或者执行相关的行为。\n" +
                "4.6.1 创建与设置砖块\n" +
                "在本游戏的设计中，为了简单起见，没有加入游戏关卡的概念，没有去设置每一关的砖块与道具等东西的分布，所以，游戏开始的时候，我们会用一个方法名为createBrickArr的方法去随机产生砖块与道具，先看以下代码。\n" +
                "代码清单：code\\ball\\src\\org\\crazyit\\ball\\BallService.java\n" +
                "public Brick[][] createBrickArr( String path, int xSize, int ySize ) throws IOException {\n" +
                "\t//创建一个Brick[][]\n" +
                "\tBrick[][] bricks = new Brick[xSize][ySize];\n" +
                "\tint x = 0;\n" +
                "\tint y = 0;\n" +
                "\tint random = 0;\n" +
                "\tint imageSize = 28;\n" +
                "\tboolean isDisable = false;\n" +
                "\t//迭代初始化数组\n" +
                "\tfor ( int i = 0 ; i <xSize ; i++ ) {\n" +
                "\t\tfor ( int j = 0 ; j < ySize ; j++) {\n" +
                "\t\t\t//创建一个新的砖块\n" +
                "\t\t\trandom = (int)( Math.random() * 3 );\n" +
                "\t\t\tx = i * imageSize;\n" +
                "\t\t\ty = j * imageSize;\n" +
                "\t\t\t//一定机率没有砖块\n" +
                "\t\t\tisDisable = Math.random() > 0.8 ? true : false;\n" +
                "\t\t\tif( isDisable ){\n" +
                "\t\t\t\trandom = 0;\n" +
                "\t\t\t}\n" +
                "\t\t\tBrick brick = new Brick( path, random, x, y );\n" +
                "\t\t\tbrick.setDisable(isDisable);\n" +
                "\t\t\t//设置x坐标\n" +
                "\t\t\tbrick.setX( x );\n" +
                "\t\t\t//设置y坐标\n" +
                "\t\t\tbrick.setY( y );\n" +
                "\t\t\tbricks[i][j] = brick;\n" +
                "\t\t}\n" +
                "\t}\t\n" +
                "\treturn bricks;\n" +
                "}\n" +
                "这个方法的返回类型是Brick[][]，也就是说是一个Brick类型的二维数组，bricks[i][j]代表砖块在第i行第j列。有三个参数：String类型的图片文件路径path，还有代表返回数组大小的xSize与ySize，这两个参数是int类型。首先，以xSize与ySize去创建一个Brick[][]类型的变量，接下来遍历这个数组，在遍历的过程中，每次先创建一个砖块，然后随机设置砖块对象的disable属性，disable属性为true的砖块将不会被显示，创建砖块的过程中，也是随机创建砖块所包含的道具，然后再设置这个砖块的x与y坐标，最后把新创建的砖块对象赋给bricks[i][j]。遍历完这个数组后，便把bricks返回。这样就完成创建游戏中所有砖块与道具的过程。\n" +
                "创建了砖块的二维数组后，我们就需要将这个二维数组“画”到BallPanel中，为BallService的draw加入相关的实现即可。\n" +
                "代码清单：code\\ball\\src\\org\\crazyit\\ball\\BallService.java\n" +
                "\t\t\t// 迭代绘制砖块图像\n" +
                "\t\t\tfor (int i = 0; i < bricks.length; i++) {\n" +
                "\t\t\t\tfor (int j = 0; j < bricks[i].length; j++) {\n" +
                "\t\t\t\t\tBallComponent magic = bricks[i][j].getMagic();\n" +
                "\t\t\t\t\t// 如果这个砖块图像对像是有效的\n" +
                "\t\t\t\t\tif (!bricks[i][j].isDisable()) {\n" +
                "\t\t\t\t\t\t// 里面的数字1为砖块图像间的间隙\n" +
                "\t\t\t\t\t\tg.drawImage(bricks[i][j].getImage(), bricks[i][j]\n" +
                "\t\t\t\t\t\t\t\t.getX(), bricks[i][j].getY(), bricks[i][j]\n" +
                "\t\t\t\t\t\t\t\t.getImage().getWidth(null) - 1, bricks[i][j]\n" +
                "\t\t\t\t\t\t\t\t.getImage().getHeight(null) - 1, null);\n" +
                "\t\t\t\t\t} else if (magic != null && magic.getY() < height) {\n" +
                "\t\t\t\t\t\tg.drawImage(magic.getImage(), magic.getX(), magic\n" +
                "\t\t\t\t\t\t\t\t.getY(), null);\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "同样地，使用嵌套循环将砖块的二维数组“画”到BallPanel中，在绘画该二维数组的时候，要判断砖块是否有效。需要注意的是，必须是游戏中时才进行绘画，当游戏结束（胜利、失败）或者小球停止运动的时候，我们并不需要绘画此二维数组。绘画砖块的具体效果如图4.8所示。\n" +
                "\n" +
                "图4.8 创建砖块\n" +
                "4.6.2 设置挡板的位置（移动挡板）\n" +
                "挡板的移动主要是依靠监听玩家的键盘操作，然后做出相应的反应，去改变挡板的坐标位置，所以需要以一个KeyEvent对象做为这个方法的参数，在方法内可以通过这个对象的getKeyCode()方法去获取玩家所按下的键盘按键，先看以下代码。\n" +
                "代码清单：code\\ball\\src\\org\\crazyit\\ball\\BallService.java\n" +
                "public void setStickPos( KeyEvent ke ) {\n" +
                "\t//把弹球的运动状态设为true\n" +
                "\tball.setStarted( true );\t\t\n" +
                "\t//如果是左方向键\n" +
                "\tif( ke.getKeyCode() == KeyEvent.VK_LEFT ) {\n" +
                "\t\tif( stick.getX() - stick.SPEED > 0 ) {\n" +
                "\t\t\t//x坐标向左移动\n" +
                "\t\t\tstick.setX( stick.getX() - stick.SPEED );\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\t//如果是右方向键\n" +
                "\tif( ke.getKeyCode() == KeyEvent.VK_RIGHT ) {\n" +
                "\t\tif( stick.getX() + stick.SPEED < width \n" +
                "\t\t\t- stick.getPreWidth() ) {\n" +
                "\t\t\t//x坐标向右移动\n" +
                "\t\t\tstick.setX( stick.getX() + stick.SPEED );\n" +
                "\t\t\t//ballFrame.getBallGame().reStart( ballFrame );\n" +
                "\t\t}\n" +
                "\t}\n" +
                "\t//如果是F2键\n" +
                "\tif( ke.getKeyCode() == KeyEvent.VK_F2 ) \t{\n" +
                "\t\t//初始化ballFrame\n" +
                "\t\ttry {\n" +
                "\t\t\tballFrame.initialize();\n" +
                "\t\t} catch ( IOException e ) {\n" +
                "\t\t\tSystem.out.println( e.getMessage() );\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n" +
                "如果玩家按下的是左键，也就是ke.getKeyCode() 等于KeyEvent.VK_LEFT，就先检查挡板是否已经在游戏面板的最左边，如果不是，就把挡板的位置向左移动Stick.SPEED个位置（SPEED代表挡板的移动速度），否则不做任何操作。如果玩家按下的是右键，处理方式与左键类似，只不过是方向相反。如果玩家按下的是F2键（这里定义F2键是重新开始游戏），就调用BallFrame对象的initialize方法是重新初始化界面。实现挡板的移动较为简单，只需要设置挡板对象的坐标并判断是否越界面即可。\n" +
                "4.6.3 小球与砖块碰撞\n" +
                "\n" +
                "图 4.9 小球与砖块碰撞\n" +
                "在游戏中，如果运行的小球碰到砖块，就要把砖块消掉，所以我们需要判断小球与砖块是否有碰撞，假设小球圆心的坐标是（x1，y1），砖块中间的坐标是（x2，y2），砖块的一半边长是n，小球的半径是r，那么，如果（x1，y1）与（x2，y2）的距离小于n+r，砖块与小球就处于碰撞的状态，见图4.9与以下代码。\n" +
                "代码清单：code\\ball\\src\\org\\crazyit\\ball\\BallService.java\n" +
                "public boolean isHitBrick( Brick brick ) {\n" +
                "\tif ( brick.isDisable() ) {\n" +
                "\t\treturn false;\n" +
                "\t}\n" +
                "\t//ball的圆心x坐标\n" +
                "\tdouble ballX = ball.getX() \n" +
                "\t\t+ ball.getImage().getWidth(null) / 2;\n" +
                "\t//ball的圆心y坐标\n" +
                "\tdouble ballY = ball.getY() \n" +
                "\t\t+ ball.getImage().getHeight(null) / 2;\n" +
                "\t//brick的中心x坐标\n" +
                "\tdouble brickX = brick.getX() \n" +
                "\t\t+ brick.getImage().getWidth(null)/2; \n" +
                "\t//brick的中心y坐标\n" +
                "\tdouble brickY = brick.getY() \n" +
                "\t\t+ brick.getImage().getHeight(null)/2; \n" +
                "\t//两个坐标点的距离\n" +
                "\tdouble distance = Math.sqrt(\n" +
                "\t\tMath.pow( ballX - brickX, 2 )\n" +
                "\t\t+ Math.pow( ballY -brickY, 2 ));\n" +
                "\t//如果两个图形重叠，返回true;\n" +
                "\tif( distance < ( ball.getImage().getWidth(null) \n" +
                "\t\t+ brick.getImage().getWidth(null) )/ 2) {\t\t\t\t\t\t\n" +
                "\t\t//使brick无效\n" +
                "\t\tbrick.setDisable( true );\n" +
                "\t\treturn true;\n" +
                "\t}\n" +
                "\treturn false;\n" +
                "}\n" +
                "粗体代码部分就是以（x1，y1），（x2，y2）两个点的距离与n、r的和比较，如果这个距离小于和，就调用Brick对象的setDisable方法把Brick对象设置为无效，并返回true。我们将砖块的二维数组“画”到BallPanel中的时候（遍历二维数组），得到每一个砖块对象，都需判断该对象的disable属性，如果该属性为true，则表示这块砖块仍然处在原来的位置，如果该属性为false，则表示这块砖块已经被小球碰撞，并出“跌落”了相应的道具，在draw的时候，就需要将道具的图片画到界面中（BallPanel），小球碰撞砖块的效果如图4.10所示。\n" +
                "\n" +
                "图4.10 小球与砖块碰撞\n" +
                "如图4.10所示，当小球与砖块发生碰撞的时候，砖块就会变成道具，并且该道具会进行下落。道具的移动、道具与挡板的碰撞我们将在下面的章节中描述。\n" +
                "4.8 本章小结\n" +
                "本章主要是通过一个弹球游戏的基本实现，向读者讲解Java的画图方法，主要是使用Graphics对像的drawImage方法去画图。在开发桌面弹球的过程中，我们将界面中的砖块抽象成一个二维数组，将游戏中的相关组件（小球、挡板）都抽象成为一个对象，并为JFrame提供了键盘监听器，当监听器接收到按键信息后，就会调用相关的方法去操作游戏中的各个对象，并将这些对象画到界面中。本章主要详细描述了键盘事件监听器、在Swing组件中画图等相关知识点。')");
        db.execSQL("insert into book_chapter(book_english_name,chapter_name,chapter_id,content)values('crazy_java','第5章 俄罗斯方块','5','5.1 俄罗斯方块简介\n" +
                "俄罗斯方块是我们最常见的游戏之一，该游戏出现过在掌上游戏机、家用游戏机、手机游戏和电脑游戏中，因此俄罗斯方块也是一个十分经典的游戏。我们可以在网上下载到各式各样的俄罗斯方块游戏，也可以在各个游戏大厅中见到网络对战形式的俄罗斯方块。一般的俄罗斯方块规则比较简单，游戏中随机出现一些方块，再对这些方块进行变换，下降到游戏界面中的不同位置，如果某一行中都填充了方块，那么该行就消除。当然，还有更复杂的俄罗斯方块，例如方块穿墙，方块消除列等。本章中我们实现一个简单的单机俄罗斯方块，让大家可以了解俄罗斯的实现原理。\n" +
                "5.2 建立界面\n" +
                "俄罗斯方块的界面包括两个部分，一个是方块堆砌的界面，另一部分是工具界面，工具界面包括一些游戏计分和游戏控制的功能。在本章中，我们只需要提供简单的暂停和开始功能，如果需要做到更为复杂的俄罗斯方块，还要提供工具栏，让用户进行各种操作。\n" +
                "5.2 方块堆砌界面\n" +
                "在本章中，方块堆砌界面使用一个GamePanel来实现，整个游戏界面使用一个MainFrame的类来实现，MainFrame继承于JFrame，GamePanel继承于JPanel。GamePanel需要重写父类的paint方法，将背景的图片画到GamePanel中。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\GamePanel.java\n" +
                "\tpublic void paint(Graphics g) {\n" +
                "\t\tg.drawImage(this.background, 0, 0, this.getWidth(), \n" +
                "\t\t\t\tthis.getHeight() , null);\n" +
                "\t}\n" +
                "我们可以将GamePanel看作是一个二维数组，俄罗斯方块中的每一个小方块看作是这个二维数组中的每一个元素，然后在paint方法中将这些小方块画到GamePanel中，这些将在下面的章节中描述。当一个大方块进行一次下降的时候，GamePanel就根据这个二维数组进行一次重绘（调用repaint方法）。\n" +
                "5.3 游戏界面\n" +
                "整个游戏界面包括一个GamePanel和另外的一个JPanel，该JPanel代表工具界面，包括分数的计算、当前游戏级别的显示、游戏的操作和显示下一个大方块。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\MainFrame.java\n" +
                "\tpublic MainFrame() {\n" +
                "\t\tBoxLayout toolPanelLayout = new BoxLayout(this.toolPanel, BoxLayout.Y_AXIS); \n" +
                "\t\tthis.toolPanel.setLayout(toolPanelLayout);\n" +
                "\t\tthis.toolPanel.setBorder(new EtchedBorder());\n" +
                "\t\tthis.toolPanel.setBackground(Color.gray);\n" +
                "\t\t//分数\n" +
                "\t\tthis.scoreTextBox.add(this.scoreTextLabel);\n" +
                "\t\tthis.scoreLabel.setText(String.valueOf(this.score));\n" +
                "\t\tthis.scoreBox.add(this.scoreLabel);\n" +
                "\t\t//级别\n" +
                "\t\tthis.levelTextBox.add(this.levelTextLabel);\n" +
                "\t\tthis.levelLabel.setText(String.valueOf(this.currentLevel));\n" +
                "\t\tthis.levelBox.add(this.levelLabel);\n" +
                "\t\t//继续按钮\n" +
                "\t\tthis.resumeLabel.setIcon(RESUME_ICON);\n" +
                "\t\tthis.resumeLabel.setPreferredSize(new Dimension(3, 25));\n" +
                "\t\tthis.resumeBox.add(this.resumeLabel);\n" +
                "\t\t//暂停按钮\n" +
                "\t\tthis.pauseLabel.setIcon(PAUSE_ICON);\n" +
                "\t\tthis.pauseLabel.setPreferredSize(new Dimension(3, 25));\n" +
                "\t\tthis.pauseBox.add(this.pauseLabel);\n" +
                "\t\t//开始\n" +
                "\t\tthis.startLabel.setIcon(START_ICON);\n" +
                "\t\tthis.startLabel.setPreferredSize(new Dimension(3, 25));\n" +
                "\t\tthis.startBox.add(this.startLabel);\n" +
                "\t\t//下一个\n" +
                "\t\tthis.nextTextBox.add(this.nextTextLabel);\n" +
                "\t\t//省略其他布局代码\n" +
                "\t}\n" +
                "最后将GamePanel和工具界面的JPanel分别放置到MainFrame中不同位置：\n" +
                "\t\tthis.add(this.gamePanel, BorderLayout.CENTER);\n" +
                "\t\tthis.add(this.toolPanel, BorderLayout.EAST);\n" +
                "以上代码中的this表示MainFrame。最后界面的效果如图5.1所示。\n" +
                "\n" +
                "图5.1 游戏主界面\n" +
                "其中继续、暂停和开始的三个按钮我们使用JLabel实现，当鼠标经过这三个按钮的时候，就改变这三个按钮的图片，因此需要加入鼠标监听器。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\MainFrame.java\n" +
                "\t\tthis.resumeLabel.addMouseListener(new MouseAdapter() {\n" +
                "\t\t\tpublic void mouseEntered(MouseEvent e) {\n" +
                "\t\t\t\tresumeLabel.setIcon(RESUME_ON_ICON);\n" +
                "\t\t\t}\n" +
                "\t\t\tpublic void mouseExited(MouseEvent e) {\n" +
                "\t\t\t\tresumeLabel.setIcon(RESUME_ICON);\n" +
                "\t\t\t}\n" +
                "\t\t\tpublic void mouseClicked(MouseEvent e) {\n" +
                "\t\t\t\t//继续方法\n" +
                "\t\t\t}\n" +
                "\t\t});\n" +
                "当鼠标经过“继续”的JLabel时，就会触发以上代码中的mouseEntered方法，将图片更改，如果鼠标离开JLabel的时候，就会触发mouseExited方法，使用JLabel显示的图片变回原来的图片。\n" +
                "5.3 创建游戏对象\n" +
                "在开始实现游戏前，我们可以先设计游戏中的各个对象，例如一个大方块我们可以将其抽象成一个对象，而这个大方块是由各个小方块组成的，因此又可以将各个小方块抽象成一个对象。那么在GamePanel中就可以将正在下降的大方块与已经下降的小方块通过paint方法展现到界面中。\n" +
                "5.3.1 设计小方块对象\n" +
                "新建一个Square对象来表示一个小方块，那么该小方块对象就需要包括三个属性，分别是该方块的图片、开始横坐标和开始纵坐标。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\object\\Square.java\n" +
                "public class Square {\n" +
                "\t//方块的图片\n" +
                "\tprivate Image image;\n" +
                "\t//开始横坐标\n" +
                "\tprivate int beginX;\n" +
                "\t//开始纵坐标\n" +
                "\tprivate int beginY;\n" +
                "\t//省略setter和getter方法\n" +
                "}\n" +
                "在Square对象中，我们并不需要提供大方块对象的属性，也就是说小方块并不需要知道自己是属于哪个大方块的，当大方块在下降的过程中，小方块就是属于大方块，而一旦大方块下降完成后，这种关系就不再存在，因此我们可以将小方块与大方块之间的关系放置到大方块的对象中。\n" +
                "5.3.2 设计大方块对象\n" +
                "大方块对象包括多个小方块，而这些小方块的不同位置都使得大方块产生不同的变化，每一个大方块都有多种变化。新建一个Piece对象来表示一个大方块，Piece包括如下属性。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\object\\Piece.java\n" +
                "public class Piece {\n" +
                "\t//该大方块所包含的小方块\n" +
                "\tprivate List<Square> squares;\n" +
                "\t//该大方块的变化\n" +
                "\tprotected List<List<Square>> changes = new ArrayList<List<Square>>();\n" +
                "\t//当前变化的索引（在changes集合中的索引）\n" +
                "\tprotected int currentIndex;\n" +
                "\t//每个小块的边长\n" +
                "\tpublic final static int SQUARE_BORDER = 16;\n" +
                "}\n" +
                "在Piece对象中，需要特别注意的是currentIndex属性，该属性表示当前大方块的变化，而大方块的变化用一个changes的集合来保存，这个集合中存放各种变化，每一种变化又包含多个小方块对象（Square）。如果需要创建一个大方块，就需要新建一个类去继承Piece类，在构造器中设定各个小方块位置，例如我们需要创建一个图5.2的正方形大方块。\n" +
                "\n" +
                "图5.2 正方形大方块\n" +
                "编写一个Piece0的类，并在构造器中设定各个小方块的位置。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\piece\\Piece0.java\n" +
                "public class Piece0 extends Piece {\n" +
                "\tpublic Piece0(Image image) {\n" +
                "\t\t//创建各个小方块，一个集合为一种变化\n" +
                "\t\tList<Square> squares = new ArrayList<Square>();\n" +
                "\t\tsquares.add(new Square(image, 0, 0));\n" +
                "\t\tsquares.add(new Square(image, 0, image.getHeight(null)));\n" +
                "\t\tsquares.add(new Square(image, image.getWidth(null), 0));\n" +
                "\t\tsquares.add(new Square(image, image.getWidth(null), age.getHeight(null)));\n" +
                "\t\t//加入到变化中\n" +
                "\t\tsuper.changes.add(squares);\n" +
                "\t\tsuper.setSquares(squares);\n" +
                "\t}\n" +
                "}\n" +
                "由于正方形的大方块是没有变化的，因此Piece对象中changes集合只有一个元素，表示该大方块只会有一种变化，最后调用Piece的setSquares方法，将唯一的一种变化设置到Piece对象中，那么这种变化就是该大方块对象的默认变化。默认变化，就是大方块第一次出现时的变化。这里需要注意的是，在构造Square（小方块对象）的时候，需要提供该对象的开始横坐标和开始纵坐标，由于在构造该小方块对象的时候，这个小方块并不需要任何一个界面组件，因此可以将左上角的一个小方块的坐标看成（0，0），而其他坐标则相应加上小方块的边长（宽）。接下来，再新建一个有多种变化的大方块，如图5.3所示。\n" +
                "\n" +
                "图5.3 多种变化的大方块\n" +
                "如图5.3中的大方块，这种方块存在于四种变化，也就是说Piece对象中的changes集合需要保存有四个元素（四个集合）。新建一个Piece1对象，与Piece0对象与样，提供构造器。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\piece\\Piece1.java\n" +
                "\tpublic Piece1(Image image) {\n" +
                "\t\t//第一种变化\n" +
                "\t\tList<Square> squares0 = new ArrayList<Square>();\n" +
                "\t\tsquares0.add(new Square(image, image.getWidth(null), image.getHeight(null)));\n" +
                "\t\tsquares0.add(new Square(image, 0, image.getHeight(null)*2));\n" +
                "\t\tsquares0.add(new Square(image, image.getWidth(null), image.getHeight(null)*2));\n" +
                "\t\tsquares0.add(new Square(image, image.getWidth(null)*2, image.getHeight(null)*2));\n" +
                "\t\t//第二种变化\n" +
                "\t\tList<Square> squares1 = new ArrayList<Square>();\n" +
                "\t\tsquares1.add(new Square(image, 0, 0));\n" +
                "\t\tsquares1.add(new Square(image, 0, image.getHeight(null)));\n" +
                "\t\tsquares1.add(new Square(image, image.getWidth(null), image.getHeight(null)));\n" +
                "\t\tsquares1.add(new Square(image, 0, image.getHeight(null)*2));\n" +
                "\t\t//第三种变化\n" +
                "\t\tList<Square> squares2 = new ArrayList<Square>();\n" +
                "\t\tsquares2.add(new Square(image, 0, 0));\n" +
                "\t\tsquares2.add(new Square(image, image.getWidth(null), 0));\n" +
                "\t\tsquares2.add(new Square(image, image.getWidth(null)*2, 0));\n" +
                "\t\tsquares2.add(new Square(image, image.getWidth(null), image.getHeight(null)));\n" +
                "\t\t//第四种变化\n" +
                "\t\tList<Square> squares3 = new ArrayList<Square>();\n" +
                "\t\tsquares3.add(new Square(image, image.getWidth(null), image.getHeight(null)));\n" +
                "\t\tsquares3.add(new Square(image, image.getWidth(null)*2, 0));\n" +
                "\t\tsquares3.add(new Square(image, image.getWidth(null)*2, image.getHeight(null)));\n" +
                "\t\tsquares3.add(new Square(image, image.getWidth(null)*2, image.getHeight(null)*2));\n" +
                "\t\tsuper.changes.add(squares0);\n" +
                "\t\tsuper.changes.add(squares1);\n" +
                "\t\tsuper.changes.add(squares2);\n" +
                "\t\tsuper.changes.add(squares3);\n" +
                "\t\t//随机获得变化\n" +
                "\t\tsuper.setSquares(getDefault());\n" +
                "\t}\n" +
                "该大方块存在有四种变化，就为其提供四个变化集合，还需要定义各个变化的小方块位置，最后加入到Piece对象的changes集合中。以上的黑体代码中，调用了Piece对象的getDefault方法到随机取得默认的变化，那么在创建Piece1对象的时候，就可随机从四种变化中得到任意一种变化作为默认变化。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\object\\Piece.java\n" +
                "\tpublic List<Square> getDefault() {\n" +
                "\t\t//从changes集合中随机得到其中一种变化\n" +
                "\t\tint defaultChange = random.nextInt(changes.size());\n" +
                "\t\t//设置当前变化的索引\n" +
                "\t\tthis.currentIndex = defaultChange;\n" +
                "\t\treturn changes.get(defaultChange);\n" +
                "\t}\n" +
                "通过getDefault方法，就可以在大方块构造的时候，随机获得变化。Piece1的构造器中创建了四种变化，分别以squares0、squares1、squares2和squares3来代表。图5.3代表的是squares0定义的变化，图5.4代表的是squares1定义的变化，图5.5代表的是squares2定义的变化，图5.6代表的是squares3定义的变化。\n" +
                "\n" +
                "图5.4 squares1的变化\n" +
                "\n" +
                "图5.5 squares2的变化\n" +
                "\n" +
                "图5.6 squares3的变化\n" +
                "编写了getDefault方法后，我们还需要明白，如果该Piece对象进行变化的时候，是从changes集合中得到下一个变化，再调用setSquares方法来设置Piece的squares属性。由于我们提供了当前的变化索引（currentIndex），因此就很容易得到changes的下一个。按照以上创建Piece0和Piece1的方法，来创建各种你需要的Piece，那么游戏中就根据这些Piece的子类来创建大方块。通过观察我们可以发现，每一个Piece的子类都是通过创建不同的变化来创建的，如果需要做得更加灵活，我们可以将这些变化通过数据库或者配置文件来进行保存，只需要编写程序去得到这些变化的信息就可以创建各种不同的大方块。本章中如果需要创建一个大方块，就需要重新编写一个Piece的子类去实现。\n" +
                "5.4 创建与显示大方块\n" +
                "在5.3中，我们已经建立了各个大方块对象，在本节，我们编写程序去创建这些大方块对象。当游戏开始时，就需要创建一个当前的大方块，还需要创建下一个大方块。为了让每次出现的大方块都拥有不同的颜色和形状，在5.3.2创建Piece对象时，就已经提供了一个getDefault方法，用来随机获得某一种大方块的默认变化。新建一个PieceCreator的接口，专门用于创建大方块，并为该接口提供一个PieceCreatorImpl的实现类。\n" +
                "5.4.1 随机读取小方块图片\n" +
                "为了不用每次都需要去读取一个Image对象，我们可以在PieceCreatorImpl中提供一个Map来保存读取过的Image对象。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\object\\impl\\PieceCreatorImpl.java\n" +
                "\tprivate Map<Integer, Image> images = new HashMap<Integer, Image>();\n" +
                "而存在于文件系统的各个小方块图片，我们统一使用规定好的格式作为图片名称，方块图片的名称如图5.7所示。\n" +
                "\n" +
                "图5.7 方块图片的名称\n" +
                "由于每个方块图片的名称都是“square”加上具体某个数字，所以我们只需要随机获得后面的数字，就可以实现每次都得到不同颜色的小方块图片了。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\object\\impl\\PieceCreatorImpl.java\n" +
                "\t//从map中得到图片对象，如果map中没有存在图片对象, 则创建\n" +
                "\tprivate Image getImage(int key) {\n" +
                "\t\tif (this.images.get(key) == null) {\n" +
                "\t\t\tImage s = ImageUtil.getImage(\"images/square\" + key + \".jpg\");\n" +
                "\t\t\tthis.images.put(key, s);\n" +
                "\t\t}\n" +
                "\t\treturn this.images.get(key);\n" +
                "\t}\n" +
                "以上的getImage方法，从Map中找图片，如果没有，则读取。需要取哪种颜色的图片，完全由参数决定，因此我们可以让getImage的参数从随机数中取得。\n" +
                "\t\t//随机得到一张方块图片\n" +
                "\t\tImage image = getImage(random.nextInt(COLOR_SIZE));\n" +
                "以上代码可以随机得到一张小方块的图片，COLOR_SIZE是小方块图片的总数量。\n" +
                "5.4.2 创建大方块对象\n" +
                "在5.3.2中，我们创建了各个Piece的子类，每个子类都提供一个构造器，这些构造器中都带有一个Image的构造参数，这个Image对象代表一个小方块的图片，只需要得到一个小方块的图片，就可以直接创建一个大方块对象。\n" +
                "在PieceCreator接口中提供一个createPiece方法，并在PieceCreatorImpl中提供实现，该方法用于创建Piece对象。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\object\\impl\\PieceCreatorImpl.java\n" +
                "\tpublic Piece createPiece(int x, int y) {\n" +
                "\t\t//随机得到一张方块图片\n" +
                "\t\tImage image = getImage(random.nextInt(COLOR_SIZE));\n" +
                "\t\tPiece piece = initPiece(image);\n" +
                "\t\treturn piece;\n" +
                "\t} \n" +
                "\t//初始化一个Piece对象\n" +
                "\tprivate Piece initPiece(Image image) {\n" +
                "\t\tPiece piece = null;\n" +
                "\t\tint pieceType = random.nextInt(SQUARE_SIZE);\n" +
                "\t\t//初始化Piece，随机创建各个大方块\n" +
                "\t\tif (pieceType == 0) {\n" +
                "\t\t\tpiece = new Piece0(image);\n" +
                "\t\t} else if (pieceType == 1) {\n" +
                "\t\t\tpiece = new Piece1(image);\n" +
                "\t\t} \n" +
                "\t\t//加入其他Piece对象\n" +
                "\t\treturn piece;\n" +
                "\t}\n" +
                "以上代码实现了PieceCreator的createPiece方法，调用了initPiece方法去随机创建一个具体的Piece对象。如果随机数为0，则创建一个正方形的大方块，如果是1，则创建一个图5.3中的大方块。如果创建有其他大方块，就需要再加入判断。createPiece方法中的两个参数我们并没有使用，这两个参数表示在哪个位置创建一个Piece对象，这由显示Piece的MainFrame来决定。例如，需要在界面中的（10，10）坐标来显示Piece，那么就需要将整个Piece对象向X轴与Y轴方向各移10。在设计Piece对象的时候，Piece就不会去保存坐标，由小方块对象来保存坐标（Square），如果需要改变Piece的坐标，就意味着要改变Piece下面所有Square对象的坐标。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\object\\Piece.java\n" +
                "\t//让Piece对象中的所有Square对象的x座标都加上参数x\n" +
                "\tpublic void setSquaresXLocation(int x) {\n" +
                "\t\tfor (int i = 0; i < this.changes.size(); i++) {\n" +
                "\t\t\tList<Square> change = this.changes.get(i);\n" +
                "\t\t\tfor (int j = 0; j < change.size(); j++) {\n" +
                "\t\t\t\tSquare s = change.get(j);\n" +
                "\t\t\t\ts.setBeginX(s.getBeginX() + x);\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "以上的setSquaresXLocation方法，让Piece中的所有Square对象的开始横坐标加上x（参数），即在界面中右移。注意，需要处理所有变化中的所有小方块。除了加上横坐标，还需要加上纵坐标。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\object\\Piece.java\n" +
                "\t//让Piece对象中的所有Square对象的y座标都加上参数y\n" +
                "\tpublic void setSquaresYLocation(int y) {\n" +
                "\t\tfor (int i = 0; i < this.changes.size(); i++) {\n" +
                "\t\t\tList<Square> change = this.changes.get(i);\n" +
                "\t\t\tfor (int j = 0; j < change.size(); j++) {\n" +
                "\t\t\t\tSquare s = change.get(j);\n" +
                "\t\t\t\ts.setBeginY(s.getBeginY() + y);\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "通过以上的设置Piece横坐标和纵坐标的方法，就可以设置整个大方块在界面中的位置。那么在创建大方块的时候，就可以调用这两个方法来设置大方块的初始位置。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\object\\impl\\PieceCreatorImpl.java\n" +
                "\tpublic Piece createPiece(int x, int y) {\n" +
                "\t\t//随机得到一张方块图片\n" +
                "\t\tImage image = getImage(random.nextInt(COLOR_SIZE));\n" +
                "\t\tPiece piece = initPiece(image);\n" +
                "\t\tpiece.setSquaresXLocation(x);\n" +
                "\t\tpiece.setSquaresYLocation(y);\n" +
                "\t\treturn piece;\n" +
                "\t}\n" +
                "以上代码的黑体部分，就设置了一个大方块创建时的初始位置。\n" +
                "5.4.3 显示当前方块\n" +
                "在实现了PieceCreator创建Piece的方法后，就可以在MainFrame中提供一个Piece对象来代表当前正在下降的大方块，再提供一个Piece对象来代表下一个大方块对象。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\MainFrame.java\n" +
                "\t//当前正在运动的对象\n" +
                "\tprivate Piece currentPiece;\n" +
                "\t//下一个大方块对象\n" +
                "\tprivate Piece nextPiece;\n" +
                "并需要为当前正在运动的大方块对象提供一个get方法，为GamePanel提供一个构造器，在构造GamePanel的时候，将MainFrame作为构造参数传入。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\GamePanel.java\n" +
                "\tMainFrame mainFrame;\n" +
                "\tpublic GamePanel(MainFrame mainFrame) {\n" +
                "\t\tthis.mainFrame = mainFrame;\n" +
                "\t}\n" +
                "在GamePanel中我们可以根据MainFrame对象得到当前界面中正在运动的Piece对象，接下来继续实现paint方法，将当前运动的Piece对象也画到GamePanel中。\n" +
                "为ImageUtil加入一个画Piece的工具方法，该方法可以进行重用。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\util\\ImageUtil.java\n" +
                "\t//在界面上画一个Piece对象\n" +
                "\tpublic static void paintPiece(Graphics g, Piece piece) {\n" +
                "\t\tif (piece == null) return;\n" +
                "\t\tfor (int i = 0; i < piece.getSquares().size(); i++) {\n" +
                "\t\t\tSquare s = piece.getSquares().get(i);\n" +
                "\t\t\t//得到各个小方块后，将这些小方块画到界面中\n" +
                "\t\t\tg.drawImage(s.getImage(), s.getBeginX(), s.getBeginY(), null);\n" +
                "\t\t}\n" +
                "\t}\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\GamePanel.java\n" +
                "\tpublic void paint(Graphics g) {\n" +
                "\t\t//画背景\n" +
                "\t\tg.drawImage(this.background, 0, 0, this.getWidth(), \tthis.getHeight() , null);\n" +
                "\t\t//画当前运动的大方块\n" +
                "\t\tPiece currentPiece = this.mainFrame.getCurrentPiece();\n" +
                "\t\tImageUtil.paintPiece(g, currentPiece);\n" +
                "\t}\n" +
                "实现MainFrame的开始游戏的方法，当开始游戏时，就创建当前块和下一块。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\MainFrame.java\n" +
                "\t//下一个Piece的位置\n" +
                "\tprivate final static int NEXT_X = 270;\n" +
                "\tprivate final static int NEXT_Y = 320;\n" +
                "\t//当前Piece的开始X座标\n" +
                "\tprivate final static int BEGIN_X = Piece.SQUARE_BORDER * 6;\n" +
                "\t//当前Piece的开始Y座标\n" +
                "\tprivate final static int BEGIN_Y = 0;\n" +
                "\t//创建下一个\n" +
                "\tprivate void createNextPiece() {\n" +
                "\t\tthis.nextPiece = this.creator.createPiece(NEXT_X, NEXT_Y);\n" +
                "\t\tthis.repaint();\n" +
                "\t}\n" +
                "\t//开始游戏\n" +
                "\tpublic void start() {\n" +
                "\t\t//创建下一个大方块\n" +
                "\t\tcreateNextPiece();\n" +
                "\t\t//创建当前运动的大方块\n" +
                "\t\tthis.currentPiece = creator.createPiece(BEGIN_X, BEGIN_Y);\n" +
                "\t}\n" +
                "当点击开始“按钮”时，就调用start方法开始游戏，创建当前大方块与下一个大方块，并显示到界面的相应位置，具体的效果如图5.8所示。\n" +
                "\n" +
                "图5.8 游戏开始\n" +
                "5.5 处理方块的行为\n" +
                "我们在5.4章节就随机创建了方块对象，各个方块在游戏中都有自己的行为，例如变化、左移、右移和快速下降。如果大方块进行变化，就需要从变化集合得到下一个变化，改变自己的pieces属性（表示当前的变化）。如果需要进行左移，就调用Piece对象的setSquaresXLocation方法让大方块对象的横坐标加上相应的值，右移就调用setSquaresXLocation方法让大方块对象的横坐标减去相应的值，setSquaresXLocation方法已经在5.4.3中实现。如果大方块需要进行快速下降，就需要调用Piece对象的setSquaresYLocation的方法让Piece对象的纵坐标加上相应的值。\n" +
                "\t5.5.1 方块变化\n" +
                "在5.4.2中创建了大方块对象（Piece），在Piece中有一个changes的集合用来保存大方块的变化，因此在界面变化方块时，就可以拿到该集合的下一个元素，再设置当前的变化属性。为Piece对象新建一个change方法：\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\object\\Piece.java\n" +
                "\tpublic void change(){\n" +
                "\t\tif (this.changes.size() == 0) return;\n" +
                "\t\tthis.currentIndex += 1;\n" +
                "\t\t//如果当前变化超过changes集合的大小, 则从0开始变化\n" +
                "\t\tif (this.currentIndex >= this.changes.size()) this.currentIndex = 0; \n" +
                "\t\tthis.squares = this.changes.get(this.currentIndex);\n" +
                "\t}\n" +
                "在change方法中，先帮当前变化的索引加一，再判断当前变化的索引是否超过变化集合（changes）的总数，如果超过变化集合的总数，则从0开始，最后从变化中取得相应的元素设置到当前的squares属性中。由于在GamePanel的paint方法中，我们是使用Piece的squares属性来画界面的，因此改变squares属性就可以达到改变方块形状的要求。\n" +
                "实现了change方法后，为界面添加键盘事件。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\MainFrame.java\n" +
                "\t\t//添加键盘监听器\n" +
                "\t\tthis.addKeyListener(new KeyAdapter() {\n" +
                "\t\t\tpublic void keyPressed(KeyEvent e) {\n" +
                "\t\t\t\t//键盘的上\n" +
                "\t\t\t\tif (e.getKeyCode() == 38) change();\n" +
                "\t\t\t}\n" +
                "\t\t});\n" +
                "以下为MainFrame中的change方法的实现。MainFrame：\n" +
                "\t//按键盘上时触发的方法\n" +
                "\tpublic void change() {\n" +
                "\t\tif (this.currentPiece == null) return;\n" +
                "\t\tthis.currentPiece.change();\n" +
                "\t\tthis.gamePanel.repaint();\n" +
                "\t}\n" +
                "这样，大方块的转换就实现了。但是，还没有完全实现，还会出现一些不理想的地方，如图5.9和5.10所示。\n" +
                "\n" +
                "图5.9 大方块变化前\n" +
                "\n" +
                "5.10 大方块变化后\n" +
                "当大方块在GamePanel的最右侧的时候，就会出现如图5.10的时的情况，同样地，如果大方块在GamePanel的最左边的时候，也会出现这种情况。因此，在转换的时候，需要作一些判断并作出相应的方块调整。为Piece对象添加两个方法，返回Piece对象的最小横坐标和最大横坐标的值。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\object\\Piece.java\n" +
                "\t//得到当前变化中x座标的最小值\n" +
                "\tpublic int getMinXLocation() {\n" +
                "\t\tint result = Integer.MAX_VALUE;\n" +
                "\t\tfor (int i = 0; i < this.squares.size(); i++) {\n" +
                "\t\t\tSquare s = this.squares.get(i);\n" +
                "\t\t\tif (s.getBeginX() < result) result = s.getBeginX();\n" +
                "\t\t}\n" +
                "\t\treturn result;\n" +
                "\t}\n" +
                "\t//得到当前变化中x座标最大的值\n" +
                "\tpublic int getMaxXLocation() {\n" +
                "\t\tint result = 0;\n" +
                "\t\tfor (int i = 0; i < this.squares.size(); i++) {\n" +
                "\t\t\tSquare s = this.squares.get(i);\n" +
                "\t\t\tif (s.getBeginX() > result) result = s.getBeginX();\n" +
                "\t\t}\n" +
                "\t\treturn result + SQUARE_BORDER;\n" +
                "\t}\n" +
                "由于Piece本身不保存横坐标和纵坐标的值，因此需要从当前变化（squares属性）中得到横坐标最小或最大的那个Square对象，并返回这个Square对象的横坐标值，这个值就是整个大方块（Piece）的最小（最大）横坐标。\n" +
                "然后对MainFrame中的change方法作出修改。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\MainFrame.java\n" +
                "\t//按键盘上时触发的方法\n" +
                "\tpublic void change() {\n" +
                "\t\tif (this.currentPiece == null) return;\n" +
                "\t\tthis.currentPiece.change();\n" +
                "\t\t//判断转换后左边是否越界\n" +
                "\t\t//得到当前方块最小的X座标\n" +
                "\t\tint minX = this.currentPiece.getMinXLocation();\n" +
                "\t\t//左边越界\n" +
                "\t\tif (minX < 0) {\n" +
                "\t\t\t//右移超过的部分\n" +
                "\t\t\tthis.currentPiece.setSquaresXLocation(-minX);\n" +
                "\t\t}\n" +
                "\t\t//判断转换后右边是否越界\n" +
                "\t\tint maxX = this.currentPiece.getMaxXLocation();\n" +
                "\t\tint gamePanelWidth = this.gamePanel.getWidth();\n" +
                "\t\t//右边越界\n" +
                "\t\tif (maxX > gamePanelWidth) {\n" +
                "\t\t\t//左移超过GamePanel宽的部分\n" +
                "\t\t\tthis.currentPiece.setSquaresXLocation(-(maxX - gamePanelWidth));\n" +
                "\t\t}\n" +
                "\t\tthis.gamePanel.repaint();\n" +
                "\t}\n" +
                "以上代码中的黑体部分就是新加的判断代码，如果左边越界，就将整个Piece对象右移超过的值；如果右边越界，就将整个Piece对象左移超过的值。左移与右移使用Piece中的setSquaresXLocation方法，这个方法已经在5.4.2中实现。加入了越界判断的代码后，再运行游戏可以看到具体的效果，已经不会再出现类似图5.10的情况了。\n" +
                "5.5.2 方块的左移和右移\n" +
                "在5.4.2中我们实现了setSquaresXLocation的方法，调用这个方法就可以改变整个Piece对象的横坐标值。下面实现方块的左移。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\MainFrame.java\n" +
                "\t//左, 参数为距离\n" +
                "\tpublic void left(int size) {\n" +
                "\t\tif (this.currentPiece == null) return;\n" +
                "\t\t//判断是否已经在最左边边界\n" +
                "\t\tif (this.currentPiece.getMinXLocation() <= 0) return;\n" +
                "\t\t//得出移动距离\n" +
                "\t\tint distance = -(Piece.SQUARE_BORDER * size);\n" +
                "\t\tthis.currentPiece.setSquaresXLocation(distance);\n" +
                "\t\tthis.gamePanel.repaint();\n" +
                "\t}\n" +
                "MainFrame的left方法，left方法提供一个size参数，表示移动的格数，再根据移动的格数乘以小方块的边长就可以得出移动的距离，最后调用Piece对象的setSquaresXLocation方法，就可以改变整个Piece对象的横坐标值。实现这些后，调用GamePanel的repaint方法重绘界面就可以实现左移。\n" +
                "右移的功能与左移一致，同样是计算出移动的距离，再设置Piece的横坐标，最后repaint即可。以下是右移的具体实现。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\MainFrame.java\n" +
                "\t//右, 参数为距离(一格)\n" +
                "\tpublic void right(int size) {\n" +
                "\t\tif (this.currentPiece == null) return;\n" +
                "\t\t//判断是否超过GamePanel的宽\n" +
                "\t\tif (this.currentPiece.getMaxXLocation() >= this.gamePanel.getWidth()) return;\n" +
                "\t\tint distance = Piece.SQUARE_BORDER * size;\n" +
                "\t\tthis.currentPiece.setSquaresXLocation(distance);\n" +
                "\t\tthis.gamePanel.repaint();\n" +
                "\t}\n" +
                "无论左移还是右移，都需要判断是否越界，如果越界就直接返回，不再往下执行。如果需要触发left和right方法，需要加入键盘监听器。在5.5.1方块变换中，已经加入了“上”的监听器。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\MainFrame.java\n" +
                "\t\t//添加键盘监听器\n" +
                "\t\tthis.addKeyListener(new KeyAdapter() {\n" +
                "\t\t\tpublic void keyPressed(KeyEvent e) {\n" +
                "\t\t\t\t//上\n" +
                "\t\t\t\tif (e.getKeyCode() == 38) change();\n" +
                "\t\t\t\t//左\n" +
                "\t\t\t\tif (e.getKeyCode() == 37) left(1);\n" +
                "\t\t\t\t//右\n" +
                "\t\t\t\tif (e.getKeyCode() == 39) right(1);\n" +
                "\t\t\t}\n" +
                "\t\t});\n" +
                "5.5.3 方块下降\n" +
                "实现方块下降，可以添加一个任务定时器实现，即每隔多少时间就设置一下Piece对象的纵坐标，然后再调用GamePanel的repaint方法。新建一个TetrisTask类处理时间间隔任务，该类继承于TimerTask，为TetrisTask提供一个构造器，并重写run方法。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\MainFrame.java\n" +
                "\t//主界面对象\n" +
                "\tprivate MainFrame mainFrame;\n" +
                "\tpublic TetrisTask(MainFrame mainFrame) {\n" +
                "\t\tthis.mainFrame = mainFrame;\n" +
                "\t}\n" +
                "\tpublic void run() {\n" +
                "\t\t//得到当前正在运动的大方块\n" +
                "\t\tPiece currentPiece = this.mainFrame.getCurrentPiece();\n" +
                "\t\t//调用Piece的setSquaresYLocation方法\n" +
                "\t\tcurrentPiece.setSquaresYLocation(Piece.SQUARE_BORDER);\n" +
                "\t\tthis.mainFrame.getGamePanel().repaint();\n" +
                "\t}\n" +
                "当游戏开始时，就在MainFrame的start方法中启动这个任务调度器：\n" +
                "MainFrame的start方法：\n" +
                "\t\t//初始化定时器\n" +
                "\t\tthis.tetrisTask = new TetrisTask(this);\n" +
                "\t\tint time = 1000 / this.currentLevel;\n" +
                "\t\tthis.timer.schedule(this.tetrisTask, 0, time);\n" +
                "以上代码启动任务调度器，需要注意的是，定时器运行间隔由级别决定，如果当前游戏的级别越高，则表示大方块的下降速度越快，以上的黑体代码实现了这一功能。方块不可能永远下降，当到达界面底部或者前面遇到障碍的时候，就需要停止下降（完成下降），这些将在下面章节实现。\n" +
                "5.5.4 方块快速下降\n" +
                "方块的快速下降与正常下降实现一样，都是调用Piece的setSquaresYLocation方法实现。与正常下降一样，在这里暂时不实现停止下降（完成下降）。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\MainFrame.java\n" +
                "\t//下加速\n" +
                "\tpublic void down() {\n" +
                "\t\tif (this.pauseFlag) return;\n" +
                "\t\tif (this.currentPiece == null) return;\n" +
                "\t\tint distance = Piece.SQUARE_BORDER;\n" +
                "\t\t//调用Piece的方法，改变纵坐标的值\n" +
                "\t\tthis.currentPiece.setSquaresYLocation(distance);\n" +
                "\t\tthis.gamePanel.repaint();\n" +
                "\t}\n" +
                "当按着键盘中的“下”时，就可以执行以上的down方法，使用方块加速向下。\n" +
                "5.5.5 判断是否停止下降\n" +
                "在一个大方块下降的过程中，如果该大方块到达了界面的底部或者遇到障碍，就需要停止下降并显示下一个大方块。判断一个大方块是否到了界面底部，需要得到大方块的最大纵坐标和界面的最高值。我们为Piece对象提供一个获取纵坐标最大值的方法。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\object\\Piece.java\n" +
                "\t//得到当前变化中Y座标最大值\n" +
                "\tpublic int getMaxYLocation() {\n" +
                "\t\tint result = 0;\n" +
                "\t\tfor (int i = 0; i < this.squares.size(); i++) {\n" +
                "\t\t\tSquare s = this.squares.get(i);\n" +
                "\t\t\tif (s.getBeginY() > result) result = s.getBeginY();\n" +
                "\t\t}\n" +
                "\t\treturn result + SQUARE_BORDER;\n" +
                "\t}\n" +
                "从Piece当前变化的集合中得到纵坐标最大的那个Square对象，并返回它的纵坐标。那么在MainFrame中可以提供一个方法，判断Piece的最大纵坐标是否超过了界面（GamePanel）的高。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\MainFrame.java\n" +
                "\t//判断是否到界面最底部\n" +
                "\tpublic boolean isButtom() {\n" +
                "\t\treturn this.currentPiece.getMaxYLocation() >= this.gamePanel.getHeight();\n" +
                "\t}\n" +
                "5.5.6 创建界面的二维数组\n" +
                "当一个Piece对象完成了下降，我们需要将这个Piece里面所有的Square对象记录在MainFrame中，我们为MainFrame提供一个二维数组，这个二维数组存放Square对象，每个完成下降后的Piece，就将自己的所有Square对象存放到这个二维数组中。下面代码初始化一个Square的二维数组。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\MainFrame.java\n" +
                "\t//初始化界面二维数组\n" +
                "\tprivate void initSquares() {\n" +
                "\t\t//得到宽可以存放的方块个数\n" +
                "\t\tint xSize = this.gamePanel.getWidth()/Piece.SQUARE_BORDER;\n" +
                "\t\t//得到高可以存放的方块个数\n" +
                "\t\tint ySize = this.gamePanel.getHeight()/Piece.SQUARE_BORDER;\n" +
                "\t\t//构造界面的二维数组\n" +
                "\t\tthis.squares = new Square[xSize][ySize];\n" +
                "\t\tfor(int i = 0; i < this.squares.length; i++) {\n" +
                "\t\t\tfor (int j = 0; j < this.squares[i].length; j++) {\n" +
                "\t\t\t\tthis.squares[i][j] = new Square(Piece.SQUARE_BORDER * i, \n" +
                "\t\t\t\t\t\tPiece.SQUARE_BORDER * j);\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "这里需要注意的是，创建二维数组时，都会创建每一个Square对象，但是这些Square对象并没有对应的图片，当一个Piece完成下降的时候，才会给这些Square设置上相应的图片。\n" +
                "那么我们就需要去为GamePanel的paint方法加入绘画这个二维数组的代码。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\GamePanel.java\n" +
                "\tpublic void paint(Graphics g) {\n" +
                "\t\tg.drawImage(this.background, 0, 0, this.getWidth(), \n" +
                "\t\t\t\tthis.getHeight() , null);\n" +
                "\t\tPiece currentPiece = this.mainFrame.getCurrentPiece();\n" +
                "\t\tImageUtil.paintPiece(g, currentPiece);\n" +
                "\t\t//绘画小方块的二维数组\n" +
                "\t\tSquare[][] squares = this.mainFrame.getSquares();\n" +
                "\t\tif (squares == null) return;\n" +
                "\t\tfor (int i = 0; i < squares.length; i++) {\n" +
                "\t\t\tfor (int j = 0; j < squares[i].length; j++) {\n" +
                "\t\t\t\tSquare s = squares[i][j];\n" +
                "\t\t\t\tif (s != null) {\n" +
                "\t\t\t\t\tg.drawImage(s.getImage(), s.getBeginX(), s.getBeginY(), this);\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "以上的黑体代码为新加的绘画小方块二维数组的代码，从MainFrame中得到二维数组，然后通过drawImage方法将每一个Square对象画到GamePanel中，由于Square对象中保存了图片、开始横坐标和纵坐标信息，因此paint方法不需要再做其他复杂的处理。\n" +
                "如果一个Piece在正常下降或者快速下降的时候，就需要判断是否到达了界面的最底部，如果到达了界面的最底部，就需要该Piece中所有的Square对象加入到界面的二维数组中。\n" +
                "MainFrame中将Piece中的Square加入二维数组的方法：\n" +
                "\t//将Piece中所有的Square都加入到二维数组中\n" +
                "\tprivate void appendToSquares() {\n" +
                "\t\tList<Square> squares = this.currentPiece.getSquares();\n" +
                "\t\t//遍历Piece中的Square\n" +
                "\t\tfor(Square square : squares) {\n" +
                "\t\t\tfor(int i = 0; i < this.squares.length; i++) {\n" +
                "\t\t\t\tfor (int j = 0; j < this.squares[i].length; j++) {\n" +
                "\t\t\t\t\t//得到当前界面中的Square\n" +
                "\t\t\t\t\tSquare s = this.squares[i][j];\n" +
                "\t\t\t\t\t//判断Square是否一致\n" +
                "\t\t\t\t\tif (s.equals(square)) this.squares[i][j] = square;\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t\tthis.gamePanel.repaint();\n" +
                "\t}\n" +
                "以上代码将当前的Piece对象中所有的Square加入到界面的二维数组中，需要注意的是黑体部分的代码，判断两个Square是否相等使用了equals方法，因此Square需要重写Object的equals方法，两个Square是否相等，判断的标准是两个Square的横坐标和纵坐标是否相等。修改down方法与定时器的run方法，当Piece下降到界面底部的时候，就调用上面的appendToSquares方法。此时再运行游戏，可以看到效果如图5.11所示。\n" +
                "\n" +
                "图5.11 大方块到达界面底部\n" +
                "5.5.7 判断是否遇到障碍\n" +
                "在5.5.5中实现了判断大方块是否到达界面的最底部，除了这个判断外，还需要判断方块下降的过程中是否遇到了障碍，也就是判断二维数组中的某个Square对象是否有图片。由于我们在创建二维数组的时候，这个数组里面存放的是一些Square对象，但是这些Square对象是没有image属性的（MainFrame的initSquares方法），当一个Square被固定到某个位置的时候，就在二维数组中查找相应的Square对象，用刚下降的Square对象替换成原来二维数组中的Square对象。简单的说，创建二维数组时，每一个Square对象都是空的（没有图片）。以下方法一个Piece下降的时候是否遇到障碍。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\MainFrame.java\n" +
                "\t//判断当前的Piece是否遇到障碍, 返回true表示遇到障碍, 返回false表示没有遇到\n" +
                "\tpublic boolean isBlock() {\n" +
                "\t\tList<Square> squares = this.currentPiece.getSquares();\n" +
                "\t\tfor (int i = 0; i < squares.size(); i++) {\n" +
                "\t\t\tSquare s = squares.get(i);\n" +
                "\t\t\t//需要拿一个Square的最大Y座标\n" +
                "\t\t\tSquare block = getSquare(s.getBeginX(), s.getBeginY() + Piece.SQUARE_BORDER);\n" +
                "\t\t\t//block非空表示遇到障碍\n" +
                "\t\t\tif (block != null) return true;\n" +
                "\t\t}\n" +
                "\t\treturn false;\n" +
                "\t}\n" +
                "\t//根据开始座标在当前界面数组中查找相应的Square\n" +
                "\tprivate Square getSquare(int beginX, int beginY) {\n" +
                "\t\tfor (int i = 0; i < this.squares.length; i++) {\n" +
                "\t\t\tfor (int j = 0; j < this.squares[i].length; j++) {\n" +
                "\t\t\t\tSquare s = this.squares[i][j];\n" +
                "\t\t\t\t//与参数的开始座标相同并且图片不为空\n" +
                "\t\t\t\tif (s.getImage() != null && (s.getBeginX() == beginX) && \n" +
                "\t\t\t\t\t\t(s.getBeginY() == beginY)) {\n" +
                "\t\t\t\t\treturn s;\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t\treturn null;\n" +
                "\t}\n" +
                "当一个Piece正在下降的时候，就到界面的二维数组中寻找对应的Square对象，如果找到的Square对象没有图片，那么在这次下降中就没有遇到障碍，如果找到的Square对象有图片，就证明遇到了障碍。遇到障碍后，就调用5.5.6中的appendToSquares方法，将当前的Piece对象中所有的Square加入到界面的二维数组中。实现了isBlock方法后，在快速下降和正常下降的方法中加入isBlock的判断，得到的效果如图5.12所示。\n" +
                "\n" +
                "图5.12 下降的方块遇到障碍\n" +
                "5.5.8 方块结束下降\n" +
                "当方块到达界面的最底或者遇到障碍，需要将当前方块设置为下一个方块，在MainFrame中我们已经提供了一个nextPiece的属性来代表下一个方块，那么只需要将下一个方块设置为当前方块就可以重新得到一个正在下降的方块，还需要调用PieceCreator来重新创建下一个的方块。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\MainFrame.java\n" +
                "\t//创建下一个\n" +
                "\tprivate void createNextPiece() {\n" +
                "\t\tthis.nextPiece = this.creator.createPiece(NEXT_X, NEXT_Y);\n" +
                "\t\tthis.repaint();\n" +
                "\t}\n" +
                "\t//一个Piece对象完成下降\n" +
                "\tprivate void finishDown() {\n" +
                "\t\t//将当前的Piece设置为下一个Piece\n" +
                "\t\tthis.currentPiece = this.nextPiece;\n" +
                "\t\t//设置新的Piece座标\n" +
                "\t\tthis.currentPiece.setSquaresXLocation(-NEXT_X);\n" +
                "\t\tthis.currentPiece.setSquaresXLocation(BEGIN_X);\n" +
                "\t\tthis.currentPiece.setSquaresYLocation(-NEXT_Y);\n" +
                "\t\tthis.currentPiece.setSquaresYLocation(BEGIN_Y);\n" +
                "\t\t//创建下一个Piece\n" +
                "\t\tcreateNextPiece();\n" +
                "\t}\n" +
                "如果一个方块在到达界面最底部或者遇到障碍时，就可以调用finishDown来执行下一个方块的下降与创建新的下一下方块。\n" +
                "5.6 消除行、计分与级别的提升\n" +
                "在5.5章节，我们已经实现了方块的下降，当方块结束下降的时候，就需要消除被全部填充小方块的行。当有方块行被消除时，就要加入一定的分数，如果分数到达一定的程序，就要提升游戏级别，游戏级别会影响方块的下降速度。\n" +
                "5.6.1 消除行\n" +
                "一个方块完成下降，就会将方块里面的小方块填充到界面的二维数组中，那么我们就可以对这个二维数组进行遍历，如果一行中都填充了小方块，那么就将该行所有的Square对象的图片设置为null，这样就可以消除该行的显示。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\MainFrame.java\n" +
                "\t//得到可以清理行集合\n" +
                "\tprivate void cleanRows() {\n" +
                "\t\t//使用一个集合保存被删除的行的索引\n" +
                "\t\tList<Integer> rowIndexs = new ArrayList<Integer>();\n" +
                "\t\tfor (int j = 0; j < this.squares[0].length; j++) {\n" +
                "\t\t\tint k = 0;\n" +
                "\t\t\tfor (int i = 0; i < this.squares.length; i++) {\n" +
                "\t\t\t\tSquare s = this.squares[i][j];\n" +
                "\t\t\t\t//如果该格有图片, 则k+1\n" +
                "\t\t\t\tif (s.getImage() != null) k++;\n" +
                "\t\t\t}\n" +
                "\t\t\t//如果整行都有图片, 则消除\n" +
                "\t\t\tif (k == this.squares.length) {\n" +
                "\t\t\t\t//再次对该行进行遍历, 设置该行所有格的图片为null\n" +
                "\t\t\t\tfor (int i = 0; i < this.squares.length; i++) {\n" +
                "\t\t\t\t\tSquare s = this.squares[i][j];\n" +
                "\t\t\t\t\ts.setImage(null);\n" +
                "\t\t\t\t}\n" +
                "\t\t\t\trowIndexs.add(j);\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "以上的cleanRows方法消除全部被填充的行，当消除了这些方块行后，我们使用了一个集合来保存被删除行的索引（以上的黑体代码），这个集合将在下面的方法中使用。消除行的具体效果如图5.13所示。\n" +
                "\n" +
                "图5.13 消除方块行\n" +
                "我们可以看到，当消除了一个方块行后，在该行上面的各个方块，仍然“飘浮”在空中，因此，在消除行后，我们还要对其他“飘浮”在空中的小方块进行处理，使它们能“下降”到相应的位置上。在cleanRows方法中，我们使用了一个集合来保存被删除的行的索引，因些可以提供一个方法，在删除行后，利用被删除行的索引来处理这些“飘浮”在空中的小方块。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\MainFrame.java\n" +
                "\t//处理行消除后其他Square的\"下降\", 参数为被删除的行的索引集合\n" +
                "\tprivate void handleDown(List<Integer> rowIndexs) {\n" +
                "\t\tif (rowIndexs.size() == 0) return;\n" +
                "\t\t//从被删除的行中拿出索引最小的行\n" +
                "\t\tint minCleanRow = rowIndexs.get(0);\n" +
                "\t\tint cleanRowSize = rowIndexs.size();\n" +
                "\t\t//处理下降的Square\n" +
                "\t\tfor (int j = this.squares[0].length - 1; j >= 0; j--) {\n" +
                "\t\t\tif (j < minCleanRow) {\n" +
                "\t\t\t\t//遍历上面的行, 即悬浮的行\n" +
                "\t\t\t\tfor (int i = 0; i < this.squares.length; i++) {\n" +
                "\t\t\t\t\tSquare s = this.squares[i][j];\n" +
                "\t\t\t\t\tif (s.getImage() != null) {\n" +
                "\t\t\t\t\t\t//得到下降前的图片\n" +
                "\t\t\t\t\t\tImage image = s.getImage();\n" +
                "\t\t\t\t\t\ts.setImage(null);\n" +
                "\t\t\t\t\t\t//得到下降后对应的Square对象, 数组的二维值要加上消除行的行数\n" +
                "\t\t\t\t\t\tSquare sdown = this.squares[i][j + cleanRowSize];\n" +
                "\t\t\t\t\t\tsdown.setImage(image);\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "以上的handleDown方法处理这些“飘浮”在空中的小方块，对界面的二维数组进行遍历，得到具体的某个“飘浮”在空中的小方块，得到这些小方块的图片后，使用一个Image对象来保存图片，再将这些小方块在二维数组中的二维索引加上消除的行数，即增大方块在二维数组中的二维值。\n" +
                "实现了handleDown方法后，就可以在消除方块行的方法最后调用handleDown方法：\n" +
                "\t//得到可以清理行集合\n" +
                "\tprivate void cleanRows() {\n" +
                "\t\t//省略消除方块行的代码\n" +
                "\t\t//处理悬浮的Square\n" +
                "\t\thandleDown(rowIndexs);\n" +
                "\t}\n" +
                "5.6.2 加入分数计算与级别提升\n" +
                "为俄罗斯方块加入分数计算十分简单，只要为MainFrame加入一个score的值，然后在每次清理了方块行后再加入相应的分数即可。如果分数到达一定值，就将级别提升。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\MainFrame.java\n" +
                "\t//加入分数\n" +
                "\tprivate void addScore() {\n" +
                "\t\t//加分\n" +
                "\t\tthis.score += 10;\n" +
                "\t\tthis.scoreLabel.setText(String.valueOf(score));\n" +
                "\t\t//如果可以被100整除, 则加一级\n" +
                "\t\tif ((this.score % 100) == 0) {\n" +
                "\t\t\tthis.currentLevel += 1;\n" +
                "\t\t\tthis.levelLabel.setText(String.valueOf(this.currentLevel));\n" +
                "\t\t\t//重新设置定时器\n" +
                "\t\t\tthis.timer.cancel();\n" +
                "\t\t\tthis.timer = new Timer();\n" +
                "\t\t\tthis.tetrisTask = new TetrisTask(this);\n" +
                "\t\t\t//设置方块下降速度\n" +
                "\t\t\tint time = 1000 / this.currentLevel;\n" +
                "\t\t\ttimer.schedule(this.tetrisTask, 0, time);\n" +
                "\t\t}\n" +
                "\t}\n" +
                "以上的addScore方法，在cleanRows方法（消除方块行）中调用，消除了一行就加10分，如果分数为100的倍数，那么就将级别提升一级并重新设置定时器，游戏提高了一个级别，MainFrame中的currentLevel属性就会增加，那么方块的下降速度将会提高。\n" +
                "5.7 游戏操作\n" +
                "在俄罗斯方块游戏中，会涉及一些简单的操作，例如游戏的暂停、继续和游戏失败的提示。游戏的开始我们在前面的章节中已经实现，本小节实现游戏的暂停与继续。\n" +
                "5.7.1 游戏的暂停\n" +
                "游戏中使用了一个定时器来处理方块的下降，如果游戏暂停话，就需要将这个定时器停止，并且屏蔽其他操作（变化、快速下降、左移和右移）。可以在MainFrame中提供一个全局的布而值来表示当前游戏的状态。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\MainFrame.java\n" +
                "\t//暂停的标识, true为暂停\n" +
                "\tprivate boolean pauseFlag = false;\n" +
                "\t//暂停游戏\n" +
                "\tpublic void pause() {\n" +
                "\t\tthis.pauseFlag = true;\n" +
                "\t\tif (this.timer != null) this.timer.cancel();\n" +
                "\t\tthis.timer = null;\n" +
                "\t}\n" +
                "还需要为其他操作加入判断，如果当前是暂停的话，就不进行其他操作。\n" +
                "if (this.pauseFlag) return;\n" +
                "5.7.2 游戏继续\n" +
                "继续游戏，只需要重新创建定时器并执行TetrisTask，但是需要作一些额外的判断，如何游戏没有开始或者正在游戏正在进行的时候，就不用再创建定时器。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\MainFrame.java\n" +
                "\t//继续游戏\n" +
                "\tpublic void resume() {\n" +
                "\t\tif (!this.pauseFlag) return;\n" +
                "\t\tthis.timer = new Timer();\n" +
                "\t\tthis.tetrisTask = new TetrisTask(this);\n" +
                "\t\tint time = 1000 / this.currentLevel;\n" +
                "\t\ttimer.schedule(this.tetrisTask, 0, time);\n" +
                "\t\tthis.pauseFlag = false;\n" +
                "\t}\n" +
                "5.7.3 判断游戏失败\n" +
                "游戏失败的标准是界面最上方如果有方块的存在，那么游戏失败。在游戏界面中我们保存了一个二维数组来表示这些小方块，我们只需要遍历二维数组作出判断即可。\n" +
                "代码清单：code\\tetris\\src\\org\\crazyit\\tetris\\ui\\MainFrame.java\n" +
                "\t//判断是否失败, true为失败, false反之\n" +
                "\tprivate boolean isLost() {\n" +
                "\t\tfor (int i = 0; i < this.squares.length; i++) {\n" +
                "\t\t\tSquare s = this.squares[i][0];\n" +
                "\t\t\tif (s.getImage() != null) return true;\n" +
                "\t\t}\n" +
                "\t\treturn false;\n" +
                "\t}\n" +
                "注意：只需要对界面中最上面的行进行遍历，这样可以提升性能。\n" +
                "5.8 本章总结\n" +
                "本章编写了一个较为简单的俄罗斯方块，详细介绍了俄罗斯方块中的各个功能的实现，例如方块的创建、下降、消除等，并实现了计分与级别的功能，将方块堆砌界面抽象成为一个二维数组，并将每个小方块抽象成为一个Square对象，而一个正在下降的大方块由一个Piece对象来代表，方块的创建、下降和消除，都是通过操作对象来实现，讲述了如何随机获得方块图片，再由这些方块图片来随机创建大方块。本章中的俄罗斯方块相对较为简单，如果需要开发更复杂的俄罗斯方块，可能还包括自定义方块、自定义游戏规则等一系列复杂的功能，这些功能都可以由用户去配置，本章中的俄罗斯方块并不是为了开发多复杂的游戏，而是希望能通过这个简单的游戏，向大家展现这个游戏的实现原理。希望本章 中这个简单的俄罗斯方块可以让大家充分了解这些基于swing的游戏原理，可以引导大家开发出更为强大与完善的游戏。')");

        //插入问题表
        //疯狂java
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','1','JAVA所定义的版本中不包括：','A、JAVA2 EE','B、JAVA2 Card','C、JAVA2 ME','D、JAVA2 HE','D')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','1','下列说法正确的是：','A、JAVA程序的main方法必须写在类里面','B、JAVA程序中可以有多个main方法','C、JAVA程序中类名必须与文件名一样','D、JAVA程序的main方法中如果只有一条语句，可以不用{}(大括号)括起来','A')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','1','变量命名规范说法正确的是：','A、变量由字母、下划线、数字、$符号随意组成；','B、变量不能以数字作为开头；','C、A和a在java中是同一个变量；','D、不同类型的变量，可以起相同的名字；','B')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','1','为一个boolean类型变量赋值时，可以使用:','A、boolean = 1;','B、boolean a = (9 >= 10);','C、boolean a=\"真\";','D、boolean a = = false;','B')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','1','以下不是合法的标识符的是：','A、STRING','B、x3x;','C、void','D、de$f','C')");

        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','2','表达式(11+3*8)/4%3的值是：','A、31','B、0','C、1','D、2','D')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','2','以下表达式不可以作为循环条件的是：','A、i++;','B、i>5;','C、bEqual = str.equals(\"q\");','D、count = = i;','A')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','2','运算符优先级别排序正确的是：','A、由高向低分别是：()、!、算术运算符、关系运算符、逻辑运算符、赋值运算符；','B、由高向低分别是：()、关系运算符、算术运算符、赋值运算符、!、逻辑运算符；','C、由高向低分别是：()、算术运算符、逻辑运算符、关系运算符、!、赋值运算符；','D、由高向低分别是：()、!、关系运算符、赋值运算符、算术运算符、逻辑运算符；','A')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','2','下列值不为true的表达式有：','A、\"john\" = = \"john\"','B、\"john\".equals(\"john\")','C、\"john\" = \"john\"','D、\"john\".equals(new String(\"john\"))','C')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','2','下面有关for循环的描述正确的是：','A、for循环体语句中，可以包含多条语句，但要用大括号括起来','B、for循环只能用于循环次数已经确定的情况','C、在for循环中，不能使用break语句跳出循环','D、for循环是先执行循环体语句，后进行条件判断','A')");

        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','3','对象的特征在类中表示为变量，称为类的：','A、对象','B、属性','C、方法','D、数据类型','B')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','3','在java中下列关于自动类型转换说法正确的是：','A、基本数据类型和String相加结果一定是字符串型','B、char类型和int类型相加结果一定是字符','C、double类型可以自动转换为int','D、char + int + double +\"\" 结果一定是double；','A')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','3','能够对数组正确初始化（或者是默认初始化）的是：','A、int[] a;','B、a = {1, 2, 3, 4, 5};','C、int[] a = new int[5]{1, 2, 3, 4, 5};','D、int[] a = new int[5];','D')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','3','在Java中,关于构造方法，下列说法错误的是：','A、构造方法的名称必须与类名相同','B、构造方法可以带参数','C、构造方法不可以重载','D、构造方法绝对不能有返回值','C')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','3','不是String类提供的合法的方法的是：','A、equals(String)','B、trim()','C、append()    StringBuffer','D、indexOf()','C')");

        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','4','在JAVA中，以下（ ）类的对象以键-值的方式存储对象：','A、java.util.List','B、java.util.ArrayList','C、java.util.HashMap','D、java.util.LinkedList','C')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','4','在Java语言中，下列关于类的继承的描述，正确的是：','A、一个类可以继承多个父类','B、一个类可以具有多个子类','C、子类可以使用父类的所有方法','D、子类一定比父类有更多的成员方法','B')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','4','下列选项中关于Java中super关键字的说法正确的是：','A、super关键字是在子类对象内部指代其父类对象的引用','B、super关键字不仅可以指代子类的直接父类，还可以指代父类的父类','C、子类通过super关键字只能调用父类的方法，而不能调用父类的属性','D、子类通过super关键字只能调用父类的属性，而不能调用父类的方法','A')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','4','在JAVA的异常处理模型中，能单独和finally语句一起使用的块是：','A、try','B、catch','C、throw','D、throws','A')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','4','下列选项中关于Java中封装的说法错误的是：','A、封装就是将属性私有化，提供共有的方法访问私有属性','B、属性的访问方法包括setter方法和getter方法','C、setter方法用于赋值，getter方法用于取值','D、包含属性的类都必须封装属性，否则无法通过编译','D')");

        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','5','Java中，如果类C是类B的子类，类B是类A的子类，那么下面描述正确的是：','A、C不仅继承了B中的成员，同样也继承了A中的成员','B、C只继承了B中的成员','C、C只继承了A中的成员','D、C不能继承A或B中的成员','A')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','5','分析选项中关于Java中this关键字的说法正确的是：','A、this关键字是在对象内部指代自身的引用','B、this关键字可以在类中的任何位置使用','C、this关键字和类关联，而不是和特定的对象关联','D、同一个类的不同对象共用一个this','A')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','5','关于面向对象的说法正确的是：','A、类可以让我们用程序模拟现实世界中的实体 ','B、有多少个实体就要创建多少个类','C、对象的行为和属性被封装在类中，外界通过调用类的方法来获得，但是要知道类的内部是如何实现','D、现实世界中的某些实体不能用类来描述','A')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','5','将类的成员的访问权限设置为默认的，则该成员能被：','A、同一包中的类访问','B、其他包中的类访问','C、所有的类访问','D、所有的类的子类访问','A')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('crazy_java','5','下面关于继承的说法，正确的是：','A、超类的对象就是子类的对象','B、一个类可以有几个超类','C、一个类只能有一个子类','D、一个类只能有一个超类','D')");


        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book1','1','JAVA所定义的版本中不包括：','A、JAVA2 EE','B、JAVA2 Card','C、JAVA2 ME','D、JAVA2 HE','D')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book1','1','下列说法正确的是：','A、JAVA程序的main方法必须写在类里面','B、JAVA程序中可以有多个main方法','C、JAVA程序中类名必须与文件名一样','D、JAVA程序的main方法中如果只有一条语句，可以不用{}(大括号)括起来','A')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book1','1','变量命名规范说法正确的是：','A、变量由字母、下划线、数字、$符号随意组成；','B、变量不能以数字作为开头；','C、A和a在java中是同一个变量；','D、不同类型的变量，可以起相同的名字；','B')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book1','1','为一个boolean类型变量赋值时，可以使用:','A、boolean = 1;','B、boolean a = (9 >= 10);','C、boolean a=\"真\";','D、boolean a = = false;','B')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book1','1','以下不是合法的标识符的是：','A、STRING','B、x3x;','C、void','D、de$f','C')");

        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book1','2','表达式(11+3*8)/4%3的值是：','A、31','B、0','C、1','D、2','D')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book1','2','以下表达式不可以作为循环条件的是：','A、i++;','B、i>5;','C、bEqual = str.equals(\"q\");','D、count = = i;','A')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book1','2','运算符优先级别排序正确的是：','A、由高向低分别是：()、!、算术运算符、关系运算符、逻辑运算符、赋值运算符；','B、由高向低分别是：()、关系运算符、算术运算符、赋值运算符、!、逻辑运算符；','C、由高向低分别是：()、算术运算符、逻辑运算符、关系运算符、!、赋值运算符；','D、由高向低分别是：()、!、关系运算符、赋值运算符、算术运算符、逻辑运算符；','A')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book1','2','下列值不为true的表达式有：','A、\"john\" = = \"john\"','B、\"john\".equals(\"john\")','C、\"john\" = \"john\"','D、\"john\".equals(new String(\"john\"))','C')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book1','2','下面有关for循环的描述正确的是：','A、for循环体语句中，可以包含多条语句，但要用大括号括起来','B、for循环只能用于循环次数已经确定的情况','C、在for循环中，不能使用break语句跳出循环','D、for循环是先执行循环体语句，后进行条件判断','A')");


        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book2','1','JAVA所定义的版本中不包括：','A、JAVA2 EE','B、JAVA2 Card','C、JAVA2 ME','D、JAVA2 HE','D')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book2','1','下列说法正确的是：','A、JAVA程序的main方法必须写在类里面','B、JAVA程序中可以有多个main方法','C、JAVA程序中类名必须与文件名一样','D、JAVA程序的main方法中如果只有一条语句，可以不用{}(大括号)括起来','A')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book2','1','变量命名规范说法正确的是：','A、变量由字母、下划线、数字、$符号随意组成；','B、变量不能以数字作为开头；','C、A和a在java中是同一个变量；','D、不同类型的变量，可以起相同的名字；','B')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book2','1','为一个boolean类型变量赋值时，可以使用:','A、boolean = 1;','B、boolean a = (9 >= 10);','C、boolean a=\"真\";','D、boolean a = = false;','B')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book2','1','以下不是合法的标识符的是：','A、STRING','B、x3x;','C、void','D、de$f','C')");

        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book2','2','表达式(11+3*8)/4%3的值是：','A、31','B、0','C、1','D、2','D')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book2','2','以下表达式不可以作为循环条件的是：','A、i++;','B、i>5;','C、bEqual = str.equals(\"q\");','D、count = = i;','A')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book2','2','运算符优先级别排序正确的是：','A、由高向低分别是：()、!、算术运算符、关系运算符、逻辑运算符、赋值运算符；','B、由高向低分别是：()、关系运算符、算术运算符、赋值运算符、!、逻辑运算符；','C、由高向低分别是：()、算术运算符、逻辑运算符、关系运算符、!、赋值运算符；','D、由高向低分别是：()、!、关系运算符、赋值运算符、算术运算符、逻辑运算符；','A')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book2','2','下列值不为true的表达式有：','A、\"john\" = = \"john\"','B、\"john\".equals(\"john\")','C、\"john\" = \"john\"','D、\"john\".equals(new String(\"john\"))','C')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book2','2','下面有关for循环的描述正确的是：','A、for循环体语句中，可以包含多条语句，但要用大括号括起来','B、for循环只能用于循环次数已经确定的情况','C、在for循环中，不能使用break语句跳出循环','D、for循环是先执行循环体语句，后进行条件判断','A')");

        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book3','1','JAVA所定义的版本中不包括：','A、JAVA2 EE','B、JAVA2 Card','C、JAVA2 ME','D、JAVA2 HE','D')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book3','1','下列说法正确的是：','A、JAVA程序的main方法必须写在类里面','B、JAVA程序中可以有多个main方法','C、JAVA程序中类名必须与文件名一样','D、JAVA程序的main方法中如果只有一条语句，可以不用{}(大括号)括起来','A')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book3','1','变量命名规范说法正确的是：','A、变量由字母、下划线、数字、$符号随意组成；','B、变量不能以数字作为开头；','C、A和a在java中是同一个变量；','D、不同类型的变量，可以起相同的名字；','B')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book3','1','为一个boolean类型变量赋值时，可以使用:','A、boolean = 1;','B、boolean a = (9 >= 10);','C、boolean a=\"真\";','D、boolean a = = false;','B')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book3','1','以下不是合法的标识符的是：','A、STRING','B、x3x;','C、void','D、de$f','C')");

        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book3','2','表达式(11+3*8)/4%3的值是：','A、31','B、0','C、1','D、2','D')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book3','2','以下表达式不可以作为循环条件的是：','A、i++;','B、i>5;','C、bEqual = str.equals(\"q\");','D、count = = i;','A')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book3','2','运算符优先级别排序正确的是：','A、由高向低分别是：()、!、算术运算符、关系运算符、逻辑运算符、赋值运算符；','B、由高向低分别是：()、关系运算符、算术运算符、赋值运算符、!、逻辑运算符；','C、由高向低分别是：()、算术运算符、逻辑运算符、关系运算符、!、赋值运算符；','D、由高向低分别是：()、!、关系运算符、赋值运算符、算术运算符、逻辑运算符；','A')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book3','2','下列值不为true的表达式有：','A、\"john\" = = \"john\"','B、\"john\".equals(\"john\")','C、\"john\" = \"john\"','D、\"john\".equals(new String(\"john\"))','C')");
        db.execSQL("insert into question(book_english_name,chapter_id,question_content,answer_a,answer_b,answer_c,answer_d,right_answer)values('book3','2','下面有关for循环的描述正确的是：','A、for循环体语句中，可以包含多条语句，但要用大括号括起来','B、for循环只能用于循环次数已经确定的情况','C、在for循环中，不能使用break语句跳出循环','D、for循环是先执行循环体语句，后进行条件判断','A')");


    }

    @Override//当数据库版本发生变化时会自动执行
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
