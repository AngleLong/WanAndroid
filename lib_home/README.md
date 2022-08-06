# [BottomNavigationView 的使用](https://blog.csdn.net/BigBoySunshine/article/details/105774561)

# BottomNavigation和Navigation的使用 联动

> 如果使用BottomNavigation和Navigation联动的话,存在一个问题,就是页面每次都会刷新的问题,所以主页改成BottomNavigation和ViewPager2的联动方式,来确保页面不进行刷新

# 关于Room的使用

> (一些具体的使用细节可以参考)[https://developer.android.com/training/data-storage/room?hl=zh-cn]

## 1.1 简单使用

```groovy
"androidx.room:room-runtime:$room_version"
"androidx.room:room-ktx:$room_ktx_version"
"androidx.room:room-compiler:$room_compiler_version" //这个是注解
```

> 其实只要记住3个步骤就可以了.

### 1. 创建数据库表(这里就是一个对象)

```kotlin
@Entity(tableName = "message")
data class MessageBean(
    @ColumnInfo(name = "message")
    val message: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
```

其中有几个常用的注解

1. @Entity 标识表的名称
2. @ColumnInfo 列的名称
3. @PrimaryKey 主键 (如果使用复合主键的话可以配合@Entity **@Entity(primaryKeys = ["xxx1", "xxx2"])**)
4. @Ignore 忽略字段 (如果父类你不能编写,但是还想忽略父类的字段 **@Entity(ignoredColumns = ["xxx"])**)

### 2. 创建增删改查的一些方法

```kotlin
@Dao
interface MessageBeanDao {

    @Insert
    fun insertAll(vararg message: MessageBean)

    @Delete
    fun delete(message: MessageBean)

    @Query("Select * From message")
    fun getAll(): Flow<List<MessageBean>>
}
```

相应的增删改查的方法,注意这是一个接口.相应的注解中有可以配置的参数.

### 3. 编写Database对象

```kotlin
@Database(entities = [MessageBean::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var appDatabase: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            return appDatabase ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "appDatabase"
                ).build()
                appDatabase = instancee
                instance
            }
        }
    }

    abstract fun messageDao(): MessageBeanDao
}
```

一种常规的写法.


## ps 有一点要特别注意,如果是多模块的情况下,每个模块都应该有kapt注解的依赖

```groovy
apply plugin: 'kotlin-kapt'

dependencies {
          kapt  "androidx.room:room-compiler:$room_version"
}
```