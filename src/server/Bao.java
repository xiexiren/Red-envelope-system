package server;

import java.util.Random;

class Bao{
	private double total; // 总钱数
	private int totalVal; // 随机生成整数，将钱数化为整数
	private int count;	  // 红包总数
	public Bao(double total, int count) {
		this.total = total;
		this.count = count;
		this.totalVal = (int)(total * 100);
	}
	public synchronized double getRandomMoney() {
		int val;
		// 当前剩余钱数 0.04 4人
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
			int temp; //剩下的金额
			while(true) {
				// 随机生成当前金额的随机数 [0,totalVal/count),尽量平均一点
				val = new Random().nextInt(totalVal/count);
				temp = totalVal - val;
				// 判断生成的金额大于0，且剩余的钱数够剩下人平分到0.01元
				if(temp*1.0/(count-1) >= 1 && val > 0) {
					//System.out.println("生成金额 ：" + val + "剩余金额 ：" + temp + "剩余人数 ：" + (count-1));
					break;
				}
			}
			totalVal = totalVal - val;
		}
		count--;
		return val/100.0;
	}
}
