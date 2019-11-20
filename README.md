## ParcelableDemo 序列化之Parcelable复杂成员的使用demo
### 核心代码
```java
public class MainBean implements Parcelable{
    private int id;
    private boolean normalStatus;
    private long gmtCreated;
    private String name;
    private BigDecimal price;
    private SecondBean secondBean;
    private List<SecondBean> secondBeanList;
    private int[] numberArray;

    public MainBean(){}

    protected MainBean(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<MainBean> CREATOR = new Creator<MainBean>() {
        @Override
        public MainBean createFromParcel(Parcel in) {
            return new MainBean(in);
        }

        @Override
        public MainBean[] newArray(int size) {
            return new MainBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    private void readFromParcel(Parcel in){
        //顺序一定要对应上
        id = in.readInt();
        normalStatus = in.readByte() != 0;
        gmtCreated = in.readLong();
        name = in.readString();
        //读取的price为String类型，需要转换为BigDecimal
        price = new BigDecimal(in.readString());
        //读取实现了Parcelable接口的对象
        secondBean = in.readParcelable(SecondBean.class.getClassLoader());
        //读取实现了Parcelable接口的对象List
        secondBeanList = in.createTypedArrayList(SecondBean.CREATOR);
        //创建（读取）int类型数组，其他数据类型以此类推
        numberArray = in.createIntArray();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        //切记非常重要：写数据（out.write..）和读数据（in.read..）的顺序一定要和声明的属性顺序对应上，
        // 不然会因读写的数据类型不一致而报异常
        parcel.writeInt(id);
        parcel.writeByte((byte) (normalStatus ? 1 : 0));
        parcel.writeLong(gmtCreated);
        parcel.writeString(name);
        //判空，因为如果price为null,也会报异常  没有写入BigDecimal的数据类型，需要转为String
        parcel.writeString(price == null ? "0" : price.toString());
        //写入对象，对象必须实现Parcelable接口
        parcel.writeParcelable(secondBean, i);
        //写入List集合
        parcel.writeTypedList(secondBeanList);
        //写入int类型数组，其他的数据类型以此类推
        parcel.writeIntArray(numberArray);
    }
}
```