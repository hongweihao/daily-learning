package mkii.finger1;

/**
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * 保证base和exponent不同时为0
 *
 * 测试：
 * base是0，exponent是2
 * base是2，exponent是3
 * base是2，exponent是-3
 * base是-2，exponent是3
 * base是-2，exponent是-3
 *
 */
public class Power {

    public double power(double base, int exponent) {
        // 作弊解法, java API
        return Math.pow(base, exponent);
    }

    public double power1(double base, int exponent) throws Exception{
        if (base == 0 && exponent == 0){
            throw new Exception("0");
        }
        if (base == 0){
            return 0;
        }
        if (exponent == 0){
            return 1;
        }

        double result = 1;
        for (int i = 0; i < Math.abs(exponent); i++){
            result *= base;
        }

        return exponent > 0 ? result : 1 / result;
    }
    public static void main(String[] args) throws Exception{
        Power power = new Power();
        System.out.println(power.power(-2,-3));
        System.out.println(power.power1(-2,-3));
    }

}
