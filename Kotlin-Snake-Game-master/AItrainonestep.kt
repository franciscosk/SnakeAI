import com.zetcode.fitness
import java.lang.Math.pow
import java.lang.Math.random

fun KItrainonestep(population : Array<Netzwerk>, achild:Int, mutationRate : Double) : Array<Netzwerk> {

    /** 1. fitness herausfinden**/
     //playMario(population)
fitness(population)

    /** die fittesten netzwerke auswählen**/
    population.sortByDescending{ it.fitness }
    var eltern= Array<Netzwerk>(10){i ->population[i]}
//eltern.forEach{ println(it.fitness)}
    /** neue population erstellen und diese mit kindern der besten netzwerke füllen**/

    var neuePopulation: Array<Netzwerk> = Array<Netzwerk>(population.size) { Netzwerk(population[0].schichten) }

/*for (netz in neuePopulation){
            for (schicht in netz.netz.indices){
            for (neuron in  netz.netz[schicht].indices){
                netz.netz[schicht][neuron].bias=eltern[].netz[schicht][neuron].bias+((random() - 0.5) * mutationRate)/i.fitness
               for ( synapse in netz.netz[schicht][neuron].synapsen.indices){
                   netz.netz[schicht][neuron].synapsen[synapse]= i.netz[schicht][neuron].synapsen[synapse]+((random() - 0.5) * mutationRate)/// i.fitness
               }

            }


        }
    }*/

    //var neuePopulation: Array<Netzwerk> = Array<Netzwerk>(population.size) { Netzwerk(population[0].schichten) }
    for(netzwerk in eltern.indices) {
        for (aaa in 0..achild/eltern.size - 2) {
            neuePopulation[netzwerk*achild/eltern.size+aaa].netz = Array(population[netzwerk].schichten.size - 1) { i ->
                Array(population[netzwerk].schichten[i]) { j ->
                    Neuron(
                        DoubleArray(population[netzwerk].schichten[i + 1]) { k -> population[netzwerk].netz[i][j].synapsen[k] + (random() - 0.5) / mutationRate*population[netzwerk].fitness},
                        0.0,
                        population[netzwerk].netz[i][j].bias + (random() - 0.5) * mutationRate/population[netzwerk].fitness
                    )
                }


            }
            neuePopulation[(netzwerk + 1) * achild / population.size - 1].netz =
                Array(population[netzwerk].schichten.size - 1) { i ->
                    Array(population[netzwerk].schichten[i]) { j ->
                        Neuron(
                            DoubleArray(population[netzwerk].schichten[i + 1]) { k -> population[netzwerk].netz[i][j].synapsen[k] },
                            0.0,
                            population[netzwerk].netz[i][j].bias
                        )
                    }
                }
        }}
    return neuePopulation

/*
    var AI1childmax : IntArray= IntArray(population.size)

    for(aaa in population.indices){
        for(aab in population.indidces){
            if(fitness[aab] > fitness[AI1childmax[aaa]]) AI1childmax[aaa] = aab
        }
        fitness[AI1childmax[aaa]] = 0.0

    }
    return Array<Netzwerk>(population.size){i -> neuePopulation[AI1childmax[i]]}
    return neuePopulation
    */

}