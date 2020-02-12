package server;

import java.util.Random;

class Bao{
	private double total; // ��Ǯ��
	private int totalVal; // ���������������Ǯ����Ϊ����
	private int count;	  // �������
	public Bao(double total, int count) {
		this.total = total;
		this.count = count;
		this.totalVal = (int)(total * 100);
	}
	public synchronized double getRandomMoney() {
		int val;
		// ��ǰʣ��Ǯ�� 0.04 4��
		if(count !=0 && totalVal / count == 1) {
			val = 1;
			totalVal = totalVal - val;
			count--;
			return val/100.0;
		}		
		if(count <= 0) {
			val = 0;
		}else if(count == 1) {
			val = totalVal;
		}else {
			int temp; //ʣ�µĽ��
			while(true) {
				// ������ɵ�ǰ��������� [0,totalVal/count),����ƽ��һ��
				val = new Random().nextInt(totalVal/count);
				temp = totalVal - val;
				// �ж����ɵĽ�����0����ʣ���Ǯ����ʣ����ƽ�ֵ�0.01Ԫ
				if(temp*1.0/(count-1) >= 1 && val > 0) {
					//System.out.println("���ɽ�� ��" + val + "ʣ���� ��" + temp + "ʣ������ ��" + (count-1));
					break;
				}
			}
			totalVal = totalVal - val;
		}
		count--;
		return val/100.0;
	}
}
