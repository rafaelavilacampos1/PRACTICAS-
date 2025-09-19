public class TextoColor {
    public  String texto;
    public  String color;

    public TextoColor(String texto, String color) {
        this.texto = texto;
        this.color = color;
    }

    public void mostrar() {
        String codigoColor;

        switch (color.toLowerCase()) {
            case "rojo":
                codigoColor = "\u001B[31m";
                break;
            case "verde":
                codigoColor = "\u001B[32m";
                break;
            case "amarillo":
                codigoColor = "\u001B[33m";
                break;
            case "azul":
                codigoColor = "\u001B[34m";
                break;
            default:
                codigoColor = "\u001B[0m"; // sin color
        }

        System.out.println(codigoColor + texto + "\u001B[0m");
    }
}
