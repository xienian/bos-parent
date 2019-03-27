package cn.itheima.help;

public class ThirdMeta {
	private static double[] ds = {-1.23,322.14,10000};
	public static void main(String[] args) {
		double max = 0;
		max = ds[0]<(ds[1]<ds[2]?ds[2]:ds[1])?(ds[1]<ds[2]?ds[2]:ds[1]):ds[0];
		System.out.println(max);
	}

}
