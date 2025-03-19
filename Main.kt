package org.example
import javax.swing.JOptionPane

fun railFenceEncrypt(texto: String, clave: Int): String {
    if (clave == 1) return texto

    val rail = Array(clave) { CharArray(texto.length) }
    var direccionAbajo = false
    var fila = 0
    var col = 0

    // Llenar la tabla de Rail Fence
    for (i in texto.indices) {
        rail[fila][col] = texto[i]
        col++

        // Cambiar la dirección al llegar a los extremos
        if (fila == 0 || fila == clave - 1) {
            direccionAbajo = !direccionAbajo
        }

        fila = if (direccionAbajo) fila + 1 else fila - 1
    }

    // Leer horizontalmente los rails para obtener el texto cifrado
    var resultado = ""
    for (i in 0 until clave) {
        for (j in 0 until texto.length) {
            if (rail[i][j] != '\u0000') {
                resultado += rail[i][j]
            }
        }
    }
    return resultado
}

fun main() {
    val mensajeOriginal = JOptionPane.showInputDialog("Dame el mensaje a cifrar:")
    val claveInput = JOptionPane.showInputDialog("Dame el número de rails (clave):")
    val clave = claveInput?.toIntOrNull() ?: 3  // Valor predeterminado en caso de que la clave no sea válida

    if (mensajeOriginal != null && mensajeOriginal.isNotEmpty()) {
        val mensajeCifrado = railFenceEncrypt(mensajeOriginal, clave)
        JOptionPane.showMessageDialog(null, "El mensaje cifrado es: $mensajeCifrado")
    } else {
        JOptionPane.showMessageDialog(null, "Por favor ingrese un mensaje válido.")
    }
}
