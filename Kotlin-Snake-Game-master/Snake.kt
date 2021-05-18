package com.zetcode

import KItrainonestep
import Netzwerk
import java.awt.EventQueue
import javax.swing.JFrame
import kotlin.math.pow

class Snake : JFrame() {

    init {

        initUI()
    }

    private fun initUI() {

        add(Board())

        title = "Snake"
        isResizable = false

        pack()

        setLocationRelativeTo(null)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {


                var population = Array<Netzwerk>(200){Netzwerk(arrayOf(900,25,20,20,20,4))}

                for ( i in 1 until 100000){
                    if (i%100==0){
                        println(i/100)}
                    var neuepopulation = KItrainonestep(population,200,0.4)
                    population= neuepopulation
                }

                population.sortByDescending { it.fitness }
                for (i in 0..10){
                    println(population[i].rechnen(doubleArrayOf(3.0,4.0,5.0))[0])


                }
            }
        }
    }

fun fitness(population: Array<Netzwerk>){

    for (netz in population){
        EventQueue.invokeLater {
            val ex = Snake()
            ex.isVisible = true
        }



    }}
