import java.util.ArrayList;
import java.util.List;

/**
 * <h1>接入号类</h1>
 *
 * @Author: CCC
 * @Date 2020/11/23 16:23
 */
public class AccessAccount {

    private String account;
    private String name;
    private String address;
    private Integer rate;
    private Integer points;

    private List<Double> upAvgStreamList;// 上行平均
    private List<Double> downAvgStreamList;// 下行平均
    private List<Double> upPeekList;// 上行峰值
    private List<Double> upValleyList;// 上行谷值
    private List<Double> downPeekList;// 下行峰值
    private List<Double> downValleyList;// 下行谷值

    private Double averageUpAvgStream = -1.0;// 上行平均
    private Double averageDownAvgStream = -1.0;// 下行平均
    private Double maxUpPeek = -1.0;// 上行峰值
    private Double minUpValley = -1.0;// 上行谷值
    private Double maxDownPeek = -1.0;// 下行峰值
    private Double minDownValley = -1.0;// 下行谷值

    private Double upFlow = -1.0;
    private Double downFlow = -1.0;


    private Double upPeekCount = 0.0;// 峰值超过签约次数
    private Double downPeekCount = 0.0;

    private boolean isInit = false;

    /**
     * 分析数据
     */
    public void analysData(){
        if (isInit){
            double sum = 0;
            for (Double d:upAvgStreamList){
                sum+=d;
            }
            averageUpAvgStream = sum/upAvgStreamList.size();
            upFlow = averageUpAvgStream*60*60*24*30/8;

            sum = 0;
            for (Double d:downAvgStreamList){
                sum+=d;
            }
            averageDownAvgStream = sum/downAvgStreamList.size();
            downFlow = averageDownAvgStream*60*60*24*30/8;

            maxUpPeek = Double.MIN_VALUE;
            for (Double d:upPeekList){
                if (maxUpPeek < d){
                    maxUpPeek = d;
                }
                if (d > rate){
                    upPeekCount++;
                }
            }

            minUpValley = Double.MAX_VALUE;
            for (Double d:upValleyList){
                if (minUpValley > d){
                    minUpValley = d;
                }
            }

            maxDownPeek = Double.MIN_VALUE;
            for (Double d:downPeekList){
                if (maxDownPeek < d){
                    maxDownPeek = d;
                }
                if (d > rate){
                    downPeekCount++;
                }
            }

            minDownValley = Double.MAX_VALUE;
            for (Double d:downValleyList){
                if (minDownValley > d){
                    minDownValley = d;
                }
            }
        }

    }

    public AccessAccount(String account) {
        this.account = account;
        this.name = "";
        this.address = "";
        this.rate = -1;
        this.points = -1;
        this.upAvgStreamList = new ArrayList<>();
        this.downAvgStreamList = new ArrayList<>();
        this.upPeekList = new ArrayList<>();
        this.upValleyList = new ArrayList<>();
        this.downPeekList = new ArrayList<>();
        this.downValleyList = new ArrayList<>();
    }



    public void init(String name,String address,
                     Integer rate, Integer points){
        this.name = name;
        this.address = address;
        this.rate = rate;
        this.points = points;
        this.isInit = true;
    }

    /**
     *
     * @param upAvgStream 平均上行
     * @param downAvgStream 平均下行
     * @param upPeek 上行峰值
     * @param upValley 上行谷值
     * @param downPeek 下行峰值
     * @param downValleydownValley 下行谷值
     */
    public void addData(double upAvgStream, double downAvgStream,
                        double upPeek, double upValley,
                        double downPeek, double downValleydownValley){
        if (upAvgStream != -1.0){
            this.upAvgStreamList.add(upAvgStream);
        }
        if (downAvgStream != -1.0){
            this.downAvgStreamList.add(downAvgStream);
        }
        if (upPeek != -1.0){
            this.upPeekList.add(upPeek);
        }
        if (upValley != -1.0){
            this.upValleyList.add(upValley);
        }
        if (downPeek != -1.0){
            this.downPeekList.add(downPeek);
        }
        if (downValleydownValley != -1.0){
            this.downValleyList.add(downValleydownValley);
        }
    }

    public Double getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(Double upFlow) {
        this.upFlow = upFlow;
    }

    public Double getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(Double downFlow) {
        this.downFlow = downFlow;
    }

    public Double getAverageUpAvgStream() {
        return averageUpAvgStream;
    }

    public void setAverageUpAvgStream(Double averageUpAvgStream) {
        this.averageUpAvgStream = averageUpAvgStream;
    }

    public Double getAverageDownAvgStream() {
        return averageDownAvgStream;
    }

    public void setAverageDownAvgStream(Double averageDownAvgStream) {
        this.averageDownAvgStream = averageDownAvgStream;
    }

    public Double getMaxUpPeek() {
        return maxUpPeek;
    }

    public void setMaxUpPeek(Double maxUpPeek) {
        this.maxUpPeek = maxUpPeek;
    }

    public Double getMinUpValley() {
        return minUpValley;
    }

    public void setMinUpValley(Double minUpValley) {
        this.minUpValley = minUpValley;
    }

    public Double getMaxDownPeek() {
        return maxDownPeek;
    }

    public void setMaxDownPeek(Double maxDownPeek) {
        this.maxDownPeek = maxDownPeek;
    }

    public Double getMinDownValley() {
        return minDownValley;
    }

    public void setMinDownValley(Double minDownValley) {
        this.minDownValley = minDownValley;
    }

    public Double getUpPeekCount() {
        return upPeekCount;
    }

    public void setUpPeekCount(Double upPeekCount) {
        this.upPeekCount = upPeekCount;
    }

    public Double getDownPeekCount() {
        return downPeekCount;
    }

    public void setDownPeekCount(Double downPeekCount) {
        this.downPeekCount = downPeekCount;
    }

    public boolean isInit() {
        return isInit;
    }

    public void setInit(boolean init) {
        isInit = init;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public List<Double> getUpAvgStreamList() {
        return upAvgStreamList;
    }

    public void setUpAvgStreamList(List<Double> upAvgStreamList) {
        this.upAvgStreamList = upAvgStreamList;
    }

    public List<Double> getDownAvgStreamList() {
        return downAvgStreamList;
    }

    public void setDownAvgStreamList(List<Double> downAvgStreamList) {
        this.downAvgStreamList = downAvgStreamList;
    }

    public List<Double> getUpPeekList() {
        return upPeekList;
    }

    public void setUpPeekList(List<Double> upPeekList) {
        this.upPeekList = upPeekList;
    }

    public List<Double> getUpValleyList() {
        return upValleyList;
    }

    public void setUpValleyList(List<Double> upValleyList) {
        this.upValleyList = upValleyList;
    }

    public List<Double> getDownPeekList() {
        return downPeekList;
    }

    public void setDownPeekList(List<Double> downPeekList) {
        this.downPeekList = downPeekList;
    }

    public List<Double> getDownValleyList() {
        return downValleyList;
    }

    public void setDownValleyList(List<Double> downValleyList) {
        this.downValleyList = downValleyList;
    }

    @Override
    public String toString() {
        return "AccessAccount{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", rate=" + rate +
                ", points=" + points +
                ", upAvgStreamList=" + upAvgStreamList +
                ", downAvgStreamList=" + downAvgStreamList +
                ", upPeekList=" + upPeekList +
                ", upValleyList=" + upValleyList +
                ", downPeekList=" + downPeekList +
                ", downValleyList=" + downValleyList +
                ", isInit=" + isInit +
                '}';
    }
}
