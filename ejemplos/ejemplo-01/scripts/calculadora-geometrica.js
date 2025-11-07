function init() {
    let perimetroTriangulo = document.getElementById("btnPerimetroTriangulo");
    let tiposTriangulo = document.getElementsByName("tipoTriangulo");
    perimetroTriangulo.addEventListener("click", calcularPerimetroTriangulo);
    tiposTriangulo.forEach(tipoTriangulo => {
        tipoTriangulo.addEventListener("click", function () {
            // Your event handling logic here
            console.log("El tipo de triangulo seleccionado es:"+this.value);
            let divTrianguloIsosceles = document.getElementById("divTrianguloIsosceles");
            let divTrianguloEquilatero = document.getElementById("divTrianguloEquilatero");
            let divTrianguloEscaleno = document.getElementById("divTrianguloEscaleno");
            divTrianguloIsosceles.classList.add("hide");
            divTrianguloEquilatero.classList.add("hide");
            divTrianguloEscaleno.classList.add("hide");
            if(this.value == 1) {
                divTrianguloIsosceles.classList.remove("hide");
            } else if(this.value == 2) {
                divTrianguloEquilatero.classList.remove("hide");
            } else if(this.value == 3) {
                divTrianguloEscaleno.classList.remove("hide");
            } else {
                console.log("Tipo de triangulo no soportado");
            }

        });
    });

}

function calcularPerimetroTriangulo() {
    let divSuccessMessage = document.getElementById("successMessage");
    let divErrorMessage = document.getElementById("errorMessage");
    divErrorMessage.classList.add("hide");
    divSuccessMessage.classList.add("hide");
    document.getElementById("successMessage")
    try {
        let lado1 = obtenerValorLado("lado-1");
        let lado2 = obtenerValorLado("lado-2");
        let lado3 = obtenerValorLado("lado-3");
        let resultado = lado1 + lado2 + lado3;
        console.log("Perimetro del tríangulo: " + resultado);
        let inputResultado = document.getElementById("resultado");
        let divResultado = document.getElementById("divResultado");
        inputResultado.value = resultado;
        divResultado.innerHTML = resultado;
        divSuccessMessage.classList.remove("hide");
    } catch (error) {
        console.log("Error: " + error.message);
        divErrorMessage.classList.remove("hide");
    }
}

function obtenerValorLado(idLado) {
    let lado = document.getElementById(idLado);
    let valorLado = parseInt(lado.value);
    if (isNaN(valorLado)) {
        console.log("El valor del lado " + idLado + " no permite hacer el calculo");
        lado.classList.add("campo-requerido");
        throw new Error("No se ha proporcionado un lado");
    } else {
        lado.classList.remove("campo-requerido");
        return valorLado;
    }
}