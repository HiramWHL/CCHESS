package zwlhw.main.client;

public class StringIndex {
	String[] StringTexts = new String[100];
	
	public StringIndex() {
		this.StringTexts[0]="CCHESS Client @神奇五鱼";
		this.StringTexts[1]="人人对战";
		this.StringTexts[2]="人机对战";
		this.StringTexts[3]="主机名";
		this.StringTexts[4]="端口号";
		this.StringTexts[5]="昵    称";
		this.StringTexts[6]="连 接";
		this.StringTexts[7]="断  开";
		this.StringTexts[8]="请选择挑战玩家：";
		this.StringTexts[9]="连接象棋服务器";
		this.StringTexts[10]="关于本软件";
		this.StringTexts[11]="错误";
		this.StringTexts[12]="端口号只能是整数";
		this.StringTexts[13]="端口号只能是0-65535的整数";
		this.StringTexts[14]="玩家姓名不能为空";
		this.StringTexts[15]="提示";
		this.StringTexts[16]="已连接到服务器";
		this.StringTexts[17]="连接服务器失败";
		this.StringTexts[18]="请选择对方名字";
		this.StringTexts[19]="已提出挑战请求，请等待回复";
		this.StringTexts[20]="隶书";
		this.StringTexts[21]="楚 河";
		this.StringTexts[22]="汉 界";
		this.StringTexts[23]="你只是运气好而已！再试一次？";
		this.StringTexts[24]="你失败了";
		this.StringTexts[25]="你太垃圾了！";
		this.StringTexts[26]="AI象棋";
		this.StringTexts[27]="该玩家名称已经被占用，请重新填写";
		this.StringTexts[28]="象棋棋服务器已关闭";
		this.StringTexts[29]="向你发起挑战";
		this.StringTexts[30]="对方接受您的挑战!您为红棋";
		this.StringTexts[31]="对方拒绝您的挑战";
		this.StringTexts[32]="对方忙碌中";
		this.StringTexts[33]="恭喜你获胜，对方认输";
	}
	
	public String back_Strings(int i) {
		return this.StringTexts[i];
	}
	
	
}
