package t0lia.coursework2

import java.io.{File, PrintWriter}
import java.net._


object AppBigSql {

  def main(args: Array[String]): Unit = {
    val sql1 = "select r.name, r.river from river r where r.river is not null"
    val allMedSql =
      """ select concat(l.city," -> ", r1.name, " -> ", r1.sea) as result from river r1
            inner join located l on r1.name = l.river
            where r1.sea = 'Mediterranean Sea'
          union
            select concat(l.city," -> ",  r2.name, " -> ",r1.name, " -> ", r1.sea) as result from river r1
            inner join river r2 on r2.river = r1.name
            inner join located l on r2.name = l.river
            where r1.sea = 'Mediterranean Sea'
          union
            select concat(l.city," -> ",   r3.name, " -> ", r2.name, " -> ",r1.name, " -> ", r1.sea) as result from river r1
              inner join river r2 on r2.river = r1.name
              inner join river r3 on r3.river = r2.name
              inner join located l on r3.name = l.river
              where r1.sea = 'Mediterranean Sea'
          union
            select concat(l.city," -> ", r4.name, " -> ", r3.name, " -> ", r2.name, " -> ",r1.name, " -> ", r1.sea) as result from river r1
            inner join river r2 on r2.river = r1.name
            inner join river r3 on r3.river = r2.name
            inner join river r4 on r4.river = r3.name
            inner join located l on r4.name = l.river
            where r1.sea = 'Mediterranean Sea' """
    val allAtlSql =
      """ select concat(l.city," -> ", r1.name, " -> ", r1.sea) as result from river r1
            inner join located l on r1.name = l.river
            where r1.sea = 'Atlantic Ocean'
          union
            select concat(l.city," -> ",  r2.name, " -> ",r1.name, " -> ", r1.sea) as result from river r1
            inner join river r2 on r2.river = r1.name
            inner join located l on r2.name = l.river
            where r1.sea = 'Atlantic Ocean'
          union
            select concat(l.city," -> ",   r3.name, " -> ", r2.name, " -> ",r1.name, " -> ", r1.sea) as result from river r1
              inner join river r2 on r2.river = r1.name
              inner join river r3 on r3.river = r2.name
              inner join located l on r3.name = l.river
              where r1.sea = 'Atlantic Ocean'
          union
            select concat(l.city," -> ", r4.name, " -> ", r3.name, " -> ", r2.name, " -> ",r1.name, " -> ", r1.sea) as result from river r1
            inner join river r2 on r2.river = r1.name
            inner join river r3 on r3.river = r2.name
            inner join river r4 on r4.river = r3.name
            inner join located l on r4.name = l.river
            where r1.sea = 'Atlantic Ocean' """


    query(allMedSql, "mediterranean.txt")
    query(allAtlSql, "atlantic.txt")

  }

  def query(sql: String, filename: String): Unit = {
    val writer = new PrintWriter(new File(filename))

    val eq = URLEncoder.encode(sql, "UTF-8")

    val url = new java.net.URL("http://kr.unige.ch/phpmyadmin/query.php?db=Mondial" + "&sql=" + eq)
    val in = scala.io.Source.fromURL(url, "iso-8859-1")
    for (line <- in.getLines) {
      writer.write(line + "\n")
    }
    writer.close()
    in.close()
  }

}

