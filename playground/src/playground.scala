package playground

import chisel3._
import chisel3.util._
import chiseltest._
import chiseltest.RawTester.test

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

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

  val layer1: Seq[Seq[Node]] = iterLayer(layer0,1,4,2)


  println("cases in layer1="+layer1.length)


  val layer2 = iterLayer(layer1,2,4,1)


  println("cases in layer2="+layer2.length)

  val layer3 = iterLayer(layer2,3,4,0)

  println("cases in layer3="+layer3.length)
  println(layer3.head.takeRight(8).map(c => c.number))
  println(layer3.head.slice(16,24).map(c => c.number))
  println(layer3.head.slice(8,16).map(c => c.number))
  println(layer3.head.take(8).map(c => c.number))



  def getNodesCombination(nodes: Seq[Seq[Node]]): Seq[Seq[Node]] = for {
    t1 <- nodes(0)
    t2 <- nodes(1)
    t3 <- nodes(2)
    t4 <- nodes(3)
    t5 <- nodes(4)
    t6 <- nodes(5)
    t7 <- nodes(6)
    t8 <- nodes(7)
  } yield Seq(t8, t7, t6, t5, t4, t3, t2, t1)

  private def addFormerLayer(layer: Seq[Seq[Node]], oldLayer: Seq[Node]) = layer.map{ c => c ++ oldLayer}

  private def getNextLayerNodes(level:Int, constrains:Int, gap:Int, nodes: Seq[Node]): Seq[Seq[Node]] = {
    val formerLayerNodes = nodes.take(8)

    Seq(1,2,3,4,5,6,7,8).map{
      c => getPossibleNodes(level,c,constrains, gap,formerLayerNodes)
    }
  }

  private def getAllLayerCases(level:Int, constrains:Int, gap:Int, nodes: Seq[Node]): Seq[Seq[Node]] = {
    addFormerLayer(getNodesCombination(getNextLayerNodes(level, constrains, gap, nodes)), nodes)
  }

  /** sort all nodes to satisfy associative condition*/
  private def sortedNodes(constrain:Int, nodes:Seq[Node]): Seq[Node] = {
    var list = new ListBuffer[Node]
    var startpoint = nodes.head.position
    for(i <- 1 to constrain;if nodes.exists(_.position == startpoint)){
      val node = nodes.filter(_.position == startpoint).head
      startpoint = node.getNextPos
      list.append(node)
    }
    list
  }

  /** @todo add more check*/
  def getPossibleNodes(level:Int, position:Int, constrain:Int,gap:Int, nodes:Seq[Node]): Seq[Node] = {
    var list = new ListBuffer[Node]
    val availableNodes = sortedNodes(constrain, nodes.takeRight(position))
    val availableNodesNum = availableNodes.length
    val iterNum = if(availableNodesNum > constrain) constrain else availableNodesNum

    for(i <- 1 to iterNum){
      val depth = availableNodes.take(i).map(_.depth).sum
      if (gap==0){
        if(availableNodes.take(i).map(_.depth).sum == position)
          list.append(Node(level, position, i, depth))
      }else if (position >= i && availableNodes.take(i).map(_.depth).sum>= (position - gap * (constrain-1))) {
      list.append(Node(level, position, i, depth))
      }
    }


    list
  }



  def iterLayer(layer: Seq[Seq[Node]], level:Int, constrain:Int, gap:Int):  Seq[Seq[Node]] = {
    val targetLayer = new ListBuffer[Seq[Node]]
    layer.foreach { i => {
      val oneLayercase: Seq[Seq[Node]] = getAllLayerCases(level, constrain, gap, i)
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

case class Node(level:Int, position:Int, number:Int, depth:Int) {

  def isFinish: Boolean = depth == position

  def getNextPos = position - depth

}
























