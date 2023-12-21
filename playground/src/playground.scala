package playground

import chisel3._
import chisel3.util._

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

/**
  * One layer cases: Seq[Node]
  *
  *
  *
  */
object playground extends App{
  println("hello chisel")

  val layer0 = Seq(Seq(
    Node(0, 8, 1, 1),
    Node(0, 7, 1, 1),
    Node(0, 6, 1, 1),
    Node(0, 5, 1, 1),
    Node(0, 4, 1, 1),
    Node(0, 3, 1, 1),
    Node(0, 2, 1, 1),
    Node(0, 1, 1, 1),
  ))

  val layer1: Seq[Seq[Node]] = iterLayer(layer0,1,4,3)
  println("cases in layer1="+layer1.length)

  val layer2 = iterLayer(layer1,2,4,3)
  println("cases in layer2="+layer2.length)

  val layer3 = iterLayer(layer2,3,4,3)
  println("cases in layer3=" + layer3.length)
  println(layer3.head.takeRight(8).map(c => c.associativeNum))
  println(layer3.head.slice(16,24).map(c => c.associativeNum))
  println(layer3.head.slice(8,16).map(c => c.associativeNum))
  println(layer3.head.take(8).map(c => c.associativeNum))


  /** Get the combination of those nodes, the all cases
    *
    * @return A Seq of Seq[Node], each element represents a possible case in this layer.
    */
  def getNextLayerCases(nodes: Seq[Seq[Node]]): Seq[Seq[Node]] = for {
    t1 <- nodes(0)
    t2 <- nodes(1)
    t3 <- nodes(2)
    t4 <- nodes(3)
    t5 <- nodes(4)
    t6 <- nodes(5)
    t7 <- nodes(6)
    t8 <- nodes(7)
  } yield Seq(t8, t7, t6, t5, t4, t3, t2, t1)

  /** Get all nodes cases in next layer
    * In each postion, generate all possible nodes according to config.
    *
    * @return A Seq of ListBuffer[Node], each element represents all the possible Nodes in this position.
    */
  private def getNextLayerNodes(level:Int, maxAssociativeNum:Int, finalLevel:Int, nodes: Seq[Node]): Seq[Seq[Node]] = {
    /** @todo why take 8 , need take all*/
    val formerLayerNodes = nodes.take(8)

    Seq(1,2,3,4,5,6,7,8).map{
      c => getPossibleNodesFromOnePostion(level, c, maxAssociativeNum, finalLevel, formerLayerNodes)
    }
  }

  private def getNextLayerCasesWithFormer(level:Int, maxAssociativeNums:Int, finalLevel:Int, nodes: Seq[Node]): Seq[Seq[Node]] = {
    getNextLayerCases(getNextLayerNodes(level, maxAssociativeNums, finalLevel, nodes)).map{ _ ++ nodes}
  }

  /** Get all nodes to satisfy associative condition
    *
    * @param nodes: all nodes which can be found
    * @param maxAssociativeNum constainss
    *
    * @return
    */
  private def getAvailableNodes(maxAssociativeNum:Int, nodes:Seq[Node]): Seq[Node] = {
    var list = new ListBuffer[Node]
    var startpoint = nodes.head.position
    for(i <- 1 to maxAssociativeNum;if nodes.exists(_.position == startpoint)){
      val node = nodes.filter(_.position == startpoint).head
      startpoint = node.getNextPos
      list.append(node)
    }
    list
  }

  /**
    *
    * @param position the position of this node
    * @param nodes all nodes from the former level
    *
    * activated check:
    * node in final level need position==depth
    * node before needs sum>= (position - finalLevel * (maxAssociativeNum-1)
    * @todo add more check*/
  def getPossibleNodesFromOnePostion(level:Int, position:Int, maxAssociativeNum:Int, finalLevel:Int, nodes:Seq[Node]): Seq[Node] = {
    var list = new ListBuffer[Node]
    val availableNodes = getAvailableNodes(maxAssociativeNum, nodes.takeRight(position))
    val availableNodesNum = availableNodes.length
    val associativeNum = availableNodesNum.min(maxAssociativeNum)

    /** See if this associative number satisfy verification*/
    for(i <- 1 to associativeNum){
      val depth = availableNodes.take(i).map(_.depth).sum
      if (finalLevel==0){
        if(availableNodes.take(i).map(_.depth).sum == position) {
          val buildNode = Node(level, position, i, depth)
          buildNode.isSingle = true
          list.append(Node(level, position, i, depth))
        }
      }else if (position >= i && availableNodes.take(i).map(_.depth).sum>= (position - (finalLevel-level) * (maxAssociativeNum - 1))) {
      list.append(Node(level, position, i, depth))
      }
    }
    list
  }



  def iterLayer(layer: Seq[Seq[Node]], level:Int, maxAssociativeNum:Int, finalLevel:Int):  Seq[Seq[Node]] = {
    val targetLayer = new ListBuffer[Seq[Node]]
    layer.foreach { i => {
      val oneLayercase: Seq[Seq[Node]] = getNextLayerCasesWithFormer(level, maxAssociativeNum, finalLevel, i)
      oneLayercase.foreach(targetLayer.append(_))
      }
    }
    targetLayer
  }
}



class PlaygroundModule extends Module{
  val in = IO(Input(UInt(5.W)))
  val out = IO(Output(UInt(5.W)))
  out := in
}

case class Node(level:Int, position:Int, associativeNum:Int, depth:Int) {
  def getNextPos = position - depth

  var isSingle = false

  var father = Seq

}
























