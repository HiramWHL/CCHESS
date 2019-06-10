package zwlhw.main.server;

public class StringIndexServer {
	String[] StringTexts = new String[100];
	
	public StringIndexServer() {
		this.StringTexts[0]="CCHESS Server @神奇五鱼";
		this.StringTexts[1]="端 口 号";
		this.StringTexts[2]="启动";
		this.StringTexts[3]="关闭";
		this.StringTexts[4]="端口号只能是整数";
		this.StringTexts[5]="错误";
		this.StringTexts[6]="端口号只能是0-65535的整数";
		this.StringTexts[7]="服务器启动成功";
		this.StringTexts[8]="提示";
		this.StringTexts[9]="服务器启动失败";
		this.StringTexts[10]="上线了";
		this.StringTexts[11]="离线了";
	}
	
	public String back_Strings(int i) {
		return this.StringTexts[i];
	}
	
	
}
