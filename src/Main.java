import java.util.*;

public class Main {

    private static Map<String, Integer> dhondt(Map<String, Integer> votos, int escanyos) {
        Map<String, Integer> reparto = new HashMap<>();
        Map<String, Integer> result = new HashMap<>();

        for(String partido : votos.keySet()) {
            reparto.put(partido, 0);
        }

        for (int i = 0; i < escanyos; i++) {

            for (String partido : votos.keySet()) {
                result.put(partido, votos.get(partido) / (reparto.get(partido) + 1));
            }

            String partido = result.keySet().stream().reduce((r1, r2) -> {
                if (result.get(r1) > result.get(r2))
                    return r1;

                return r2;
            }).get();

            int e = reparto.get(partido);

            reparto.put(partido, ++e);
        }

        return reparto;
    }

    public static void main(String[] args) {
        String[] nombre = new String[]{"PP", "PSOE", "VOX", "SUMAR", "CS", "UP", "OTROS", "CEUS", "AHORA REPÚBLICAS", "JUNTS1"};
        //String[] nombre = new String[]{"A", "B", "C", "D", "E"};

        Map<String, Integer> votos = new HashMap<>();
        int escanyos = 61;

        System.out.println("Sistema dHondt con " + nombre.length + " partidos, " + escanyos + " escaños.");

        for (int i = 0; i < nombre.length; i++) {
            System.out.println("Votos " + nombre[i] + ": ");
            Scanner s = new Scanner(System.in);
            votos.put(nombre[i], s.nextInt());
        }

        Map<String, Integer> reparto = dhondt(votos, escanyos);

        reparto.forEach((p, e) -> System.out.println(p + ": " + e));
    }
}
