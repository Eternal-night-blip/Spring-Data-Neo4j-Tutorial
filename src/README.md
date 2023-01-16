# <center> Spring Data Neo4j MoiveDataset Tutorial 1.0
### <center> 李毅佳
### <center> Email: 2314181884@qq.com

这是Neo4j 官方数据集Movie的Spring Data Neo4j项目1.0版本，使用Gradle（7.6）进行构建与管理
## 本项目依赖配置：
SpringBoot 3.0.1版本
Java 17
Spring Data Neo4j（7.0.0） 由io.spring.dependency-management进行版本管理

采用Spring Data Neo4j框架，使用SpringBoot进行管理。
这里对Neo4j数据库进行的映射框架是Spring Data Neo4j，而不是用Neo4j OGM框架。Spring Data Neo4j自从6.0+后就不再依赖Neo4j OGM，而是作为独立的OGM框架。

对Neo4j进行本地连接，你需要对设置application.properties文件（/src/main/resources目录下）进行设置，尤其注意密码要和自己的数据库密码一致。
@import "/src/main/resources/application.properties" {line_end=3}

## 启动命令
```
./gradlew bootRun
```

## 如何学习Spring Data Neo4j
首先是通过项目进行学习，本项目是其中一个教程。
对于不熟悉的注解、API等，可以通过“Ctrl + 点击”进入到注解或者API内部查看源码，或者鼠标移至注解或API可以查看其解释（源码内部中也有）。
此外一定要阅读官方文档：https://docs.spring.io/spring-data/neo4j/docs/current/reference/html/#preface
最后深入学习源码可以帮助理解框架，并且改良框架。Spring Data Neo4j的源码地址在：https://github.com/spring-projects/spring-data-neo4j

## 教程
我们知道Neo4j有节点与关系(边)，节点有标签与属性，关系有类型与属性。
为了进行数据映射，我们需要创建节点的类，与关系的类。
这里节点的类都放在src/main/java/com/ilvo/neo4jdemo/nodes包下
关系的类都放在src/main/java/com/ilvo/neo4jdemo/relationships包下

#### 如何对Neo4j的数据模型与Java类进行映射呢？
以Person类为例
@import "/src/main/java/com/ilvo/neo4jdemo/nodes/Person.java" {line_begin=16 line_end=42}

###### 首先这个类如何识别标签呢？
用注解@Node，注意默认是类名当做标签名，为了简单起见，使用@Node("Person")，注解@Node括号里的是标签名，防止标签与类名不一致的情况。
每个节点与关系都需要属性作为唯一标识符，用注解@Id进行标记。Neo4j数据库自己生成的Id（并不是作为属性），但是Movie数据集中没有定义Id属性，所以我们把Person的name属性作为唯一标识符。这样做当然有缺陷，因为可能出现重名的情况。
其他属性用@Property进行标记，同样的括号里要写上对应的属性名。当然了你可以不用@Property标记属性，只要类中的属性名与Neo4j数据库中的该节点的属性名一致即可，但是建议使用@Property方便阅读，且不容易犯错。

###### 如何识别关系呢？
关系(边)是有方向的，并且有的关系是有属性的。
对于没有属性的关系，我们以DIRECTED关系为例，是Person类节点指向Movie类节点。
有两种办法，第一种是Person类里创建Movie类型的属性，并且标记为关系，用注解@Relationship，如下段代码所示
@import "/src/main/java/com/ilvo/neo4jdemo/nodes/Person.java" {line_begin=34 line_end=36}
注意了，这里好像没有说方向呀，实际上默认方向是OUTCOMING。在这个例子中也就是说代表了从Person指向Movie的关系DIRECTED
注意哦，这里的实际类型是"Set<Movie>"，这是因为一个导演可以指导多部电影，Set是集合，"<  >"里放具体的类型。

对于有属性的关系，我们需要单独定义这样的关系的类，比如ActedIn类
@import "/src/main/java/com/ilvo/neo4jdemo/relationships/ActedIn.java" {line_begin=10 line_end=21}
这里我们可以对应Neo4j生成的Id(并不是属性形式的Id)，那么怎么做呢?
@import "/src/main/java/com/ilvo/neo4jdemo/relationships/ActedIn.java" {line_begin=13 line_end=15}
只需要加上@RelationshipId注解，实际上这个注解是由@Id与@GeneratedValue两个注解合并成的。

对于有属性的关系，我们需要使用注解@RelationshipProperties进行标记。
注意，里面有一个@TargetNode注解，这是干嘛的呢？是要求标记另一个方向的节点类，如果这个方向是OUTCOMING，那么@TargetNode标记被指向的节点的类，
如果是INCOMING方向，那么@TargetNode标记关系起始的节点类。
这样子，Spring Data Neo4j框架就能识别有属性的关系ActedIn与其起始节点Person、终止节点Movie。

#### 如何用Spring Data Neo4j操纵Neo4j呢？
有两种方式，第一种是在实体类中进行操作，然后在Repository中保存，另一种是直接在Repositoy中进行操作。
###### 为什么要有Repository呢？
因为Repository是Java等非数据库语言操纵数据库必须要通过的方法层。
强大的Spring Data Neo4j框架提供的Repository接口很多，里面有很多定义好的方法，可以直接使用比如save方法。
它支持非Cypher语句操作Neo4j（这是OGM框架的主要功能），同时对复杂的Cypher语句也提供了原生Cypher的支持。
不用原生Cypher进行查询，例子如下：
@import "/src/main/java/com/ilvo/neo4jdemo/repository/PersonRepository.java" {line_begin=11 line_end=12}
用原生Cypher进行查询，例子如下：
@import "/src/main/java/com/ilvo/neo4jdemo/repository/PersonRepository.java" {line_begin=13 line_end=15}

关于Repository的用法与非原生Cypher进行查询的规则见：
https://docs.spring.io/spring-data/neo4j/docs/current/reference/html/#repositories
https://docs.spring.io/spring-data/neo4j/docs/current/reference/html/#repository-query-keywords

另外直接在实体类中进行操作也是可以的，比如在Person类添加FOLLOWS关系
@import "/src/main/java/com/ilvo/neo4jdemo/nodes/Person.java" {line_begin=54 line_end=60}
当然了，仅仅有这个是不够的，还得配合Repository才行，比如要用save方法进行保存，不然是无法将操作输入到Neo4j数据库中的。
相关操作在Neo4jdemoApplication.java中，其实可以专门定义某个类来综合此操作，后续版本将实现命令行与网络请求两种方式。
对应的Repositpry的操作是save。
@import "/src/main/java/com/ilvo/neo4jdemo/Neo4jdemoApplication.java" {line_begin=112 line_end=117}

比如在Person类查询与某部电影有关的ACTEDIN关系的属性
@import "/src/main/java/com/ilvo/neo4jdemo/nodes/Person.java" {line_begin=98 line_end=112}
注意这里的返回类型是ActedInProperty（在/src/main/java/ilvo/neo4jdemo/dto目录下），为什么不直接用ActedIn呢？
因为只需要返回属性，而ActedIn里面不止有属性还有Movie、id。我们把像ActedInProperty这样用于传输数据的类叫做DTO(Data Transfer Object)。
对应的Repository的操作是findByName与findActedInMovies
@import "/src/main/java/com/ilvo/neo4jdemo/Neo4jdemoApplication.java" {line_begin=180 line_end=195}
