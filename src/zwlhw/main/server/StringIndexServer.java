package zwlhw.main.server;

public class StringIndexServer {
	String[] StringTexts = new String[100];
	
	public StringIndexServer() {
		this.StringTexts[0]="CCHESS Server @��������";
		this.StringTexts[1]="�� �� ��";
		this.StringTexts[2]="����";
		this.StringTexts[3]="�ر�";
		this.StringTexts[4]="�˿ں�ֻ��������";
		this.StringTexts[5]="����";
		this.StringTexts[6]="�˿ں�ֻ����0-65535������";
		this.StringTexts[7]="�����������ɹ�";
		this.StringTexts[8]="��ʾ";
		this.StringTexts[9]="����������ʧ��";
		this.StringTexts[10]="������";
		this.StringTexts[11]="������";
	}
	
	public String back_Strings(int i) {
		return this.StringTexts[i];
	}
	
	
}
