import java.lang.Math.random

class Netzwerk(var schichten : Array<Int>, var fitness:Double=0.0) {
    /**
     Dem Constructor wird ein IntArray übergeben, bei dem jeder Integer die Anzahl der Neuronen einer Schicht darstellt.
     Mit diesem Array wird dann das tatsächliche Netz (var Netz)berstellt
     die Fitness kann im vorhinein übergeben werden, sie wird jedoch beim Späteren lernen Überschrieben werden

     **/
    var netz = Array(schichten.size-1){i -> Array(schichten[i]){Neuron(DoubleArray(schichten[i+1]) { (random()-0.5)*5 }, 0.0, (random()-0.5)*5)} }

    fun rechnen(Input: DoubleArray) : DoubleArray{
        for(a in 0 until schichten[0]){
            netz[0][a].wert = Input[a]
        }
        for(schicht in 1 until netz.size){
            for(neuron in netz[schicht].indices){
                var tmp : Double = 0.0
                for(neuronVorgänger in netz[schicht-1].indices){
                    tmp+= netz[schicht-1][neuronVorgänger].wert*netz[schicht-1][neuronVorgänger].synapsen[neuron]
                }
                netz[schicht][neuron].wert = ReLU(tmp+netz[schicht][neuron].bias)
            }
        }
        var output = DoubleArray(schichten.last()){0.0}
        for(outputNeuron in output.indices){
            var tmp : Double = 0.0
            for(i in netz.last().indices){
                tmp+= netz.last()[i].wert*netz.last()[i].synapsen[outputNeuron]
            }
            output[outputNeuron] = ReLU(tmp)
        }
        return output
    }

    fun print(){
        for(a in netz.indices){
            for(b in netz[a].indices){
                print("(")
                print(netz[a][b].bias)
                print(",")
                for(aac in netz[a][b].synapsen.indices){
                    print(netz[a][b].
                    synapsen[aac])
                    print(" ")
                }
                print(")")
            }
            println()
        }
    }
    override fun toString() : String {
        var s = ""
        for(a in 0..netz.size-1){
            for(b in 0..netz[a].size-1){
                s+= netz[a][b].bias
                s+= " "
                for(c in netz[a][b].synapsen.indices){
                    s+= netz[a][b].synapsen[c]
                    s+= " "
                }
            }
            s+="a"
        }
        return s
    }
}
fun ReLU (Input : Double) : Double {
    return if(Input>0) Input
    else 0.0
}
