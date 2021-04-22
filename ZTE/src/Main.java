/**
 * <h1></h1>
 *
 * @Author: CCC
 * @Date 2021/1/5 8:45
 */
public class Main {



    public static String printOnePon(int board,int port,PonSVlan ponSVlan){
        String res = "";
        res+="interface epon-olt_1/"+board+"/"+port+"\n" +
                "vlan-smart-qinq enable \n" +
                "exit\n";
        res += "vlan-smart-qinq ingress-port epon-olt_1/"+board+"/"+port+" cvlan 46 to 46 svlan "+ponSVlan.sv46+" newcos 7\n";
        res += "vlan-smart-qinq ingress-port epon-olt_1/"+board+"/"+port+" cvlan 43 to 43 svlan "+ponSVlan.sv43+" newcos 5\n";
        res += "vlan-smart-qinq ingress-port epon-olt_1/"+board+"/"+port+" cvlan 45 to 45 svlan "+ponSVlan.sv45+" newcos 7\n";
        res += "vlan-smart-qinq ingress-port epon-olt_1/"+board+"/"+port+" cvlan 128 to 1051 svlan "+ponSVlan.sv128to1051+" newcos 0\n";
        res += "vlan-smart-qinq ingress-port epon-olt_1/"+board+"/"+port+" cvlan 42 to 42 svlan "+ponSVlan.sv42+" newcos 6\n";
        res += "vlan-smart-qinq ingress-port epon-olt_1/"+board+"/"+port+" cvlan 32 to 32 svlan "+ponSVlan.sv32+" newcos 0\n";
        res += "vlan-smart-qinq ingress-port epon-olt_1/"+board+"/"+port+" cvlan 4001 to 4002 svlan "+ponSVlan.sv4001to4002+" newcos 0\n";
        res += "vlan-smart-qinq ingress-port epon-olt_1/"+board+"/"+port+" cvlan 1400 to 1700 svlan "+ponSVlan.sv1400to1700+" newcos 0\n";

        return res;

    }

    public static void main(String[] args) {
        PonSVlan ponSVlan = new PonSVlan(141,1637);

        String res = "";
        for (int i = 7;i<=8;i++){
            for (int j = 1;j<=8;j++){
                res+=printOnePon(i,j,ponSVlan)+"\n";
                ponSVlan.addPort();
            }
        }

        System.out.println(res);
    }
}

class PonSVlan{
    int board;
    int port;
    int sv46;
    int sv43;
    int sv45;
    int sv128to1051;
    int sv42;
    int sv32;
    int sv4001to4002;
    int sv1400to1700;

    /**
     *
     * @param sv46
     * @param sv43 FTTB????
     * @param sv45 ??????????
     * @param sv128to1051 ????
     * @param sv42
     * @param sv32
     * @param sv4001to4002 FTTB????
     * @param sv1400to1700 ??????
     */
    public PonSVlan(int sv46, int sv43, int sv45, int sv128to1051, int sv42, int sv32, int sv4001to4002, int sv1400to1700) {
        this.sv46 = sv46;
        this.sv43 = sv43;
        this.sv45 = sv45;
        this.sv128to1051 = sv128to1051;
        this.sv42 = sv42;
        this.sv32 = sv32;
        this.sv4001to4002 = sv4001to4002;
        this.sv1400to1700 = sv1400to1700;
    }

    public PonSVlan(int sv128to1051,int sv1400to1700){
        this(sv128to1051/100*100+1,
                sv128to1051+1600,
                sv128to1051,
                sv128to1051,
                sv128to1051+2400,
                sv128to1051,
                sv128to1051/100*100+2401,
                sv1400to1700);
    }

    public void addPort(){
        sv43++;
        sv45++;
        sv128to1051++;
        sv42++;
        sv32++;
    }
}
