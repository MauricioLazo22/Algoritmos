package Laboratorio-10.btree;

import java.io.*;
import java.util.*;

public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(E cl) {
        up = false;
        E mediana;
        BNode<E> pnew;
        mediana = push(this.root, cl);
        if (up) {
            pnew = new BNode<E>(this.orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }
    }

    private E push(BNode<E> current, E cl) {
        int pos[] = new int[1];
        E mediana;
        if (current == null) {
            up = true;
            nDes = null;
            return cl;
        } else {
            boolean fl;
            fl = current.searchNode(cl, pos);
            if (fl) {
                System.out.println("Item duplicado\n");
                up = false;
                return null;
            }
            mediana = push(current.childs.get(pos[0]), cl);
            if (up) {
                if (current.nodeFull(this.orden - 1)) {
                    mediana = dividedNode(current, mediana, pos[0]);
                } else {
                    up = false;
                    putNode(current, mediana, nDes, pos[0]);
                }
                return mediana;
            } else {
                return null;
            }
        }
    }

    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        int i;
        for (i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        current.keys.set(k, cl);
        current.childs.set(k + 1, rd);
        current.count++;
    }

    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        int i, posMdna;
        posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;
        nDes = new BNode<E>(this.orden);
        for (i = posMdna; i < this.orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }
        nDes.count = (this.orden - 1) - posMdna;
        current.count = posMdna;
        if (k <= this.orden / 2) {
            putNode(current, cl, rd, k);
        } else {
            putNode(nDes, cl, rd, k - posMdna);
        }
        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;
        up = true;
        return median;
    }

    public boolean search(E cl) {
        return searchSupport(this.root, cl);
    }

    private boolean searchSupport(BNode<E> node, E cl) {
        if (node == null) return false;

        int[] pos = new int[1];
        boolean found = node.searchNode(cl, pos);
        if (found) {
            System.out.println(cl + " se encuentra en el nodo " + node.idNode + " en la posición " + pos[0]);
            return true;
        } else {
            // Buscar recursivamente en el hijo correspondiente
            return searchSupport(node.childs.get(pos[0]), cl);
        }
    }

    public boolean remove(E cl) {
        if (isEmpty()) {
            System.out.println("El árbol está vacío, no se puede eliminar.");
            return false;
        }
        boolean[] shrink = new boolean[1]; // Bandera para indicar reducción de altura
        boolean result = removeSupport(this.root, cl, shrink);
        if (root.count == 0 && root.childs.get(0) != null) {
            root = root.childs.get(0); // Propaga la raíz hacia abajo si se quedó vacía
        }
        if (result)
            System.out.println("Clave " + cl + " eliminada correctamente.");
        else
            System.out.println("Clave " + cl + " no encontrada.");
        return result;
    }

    private boolean removeSupport(BNode<E> node, E cl, boolean[] shrink) {
        int[] pos = new int[1];
        boolean found = node.searchNode(cl, pos);

        if (found) {
            if (node.childs.get(0) == null) { // Es hoja
                removeFromLeaf(node, pos[0]);
            } else { // Es nodo interno
                // Reemplazar con predecesor
                BNode<E> pred = node.childs.get(pos[0]);
                while (pred.childs.get(pred.count) != null) {
                    pred = pred.childs.get(pred.count);
                }
                E predKey = pred.keys.get(pred.count - 1);
                node.keys.set(pos[0], predKey);
                removeSupport(node.childs.get(pos[0]), predKey, shrink);
            }
        } else {
            if (node.childs.get(0) == null) {
                shrink[0] = false;
                return false;
            }
            boolean res = removeSupport(node.childs.get(pos[0]), cl, shrink);
            if (shrink[0]) {
                adjust(node, pos[0], shrink);
            }
            return res;
        }

        // Después de eliminar, comprobar si hay que ajustar
        int minKeys = (orden - 1) / 2;
        if (node != root && node.count < minKeys) {
            shrink[0] = true;
        } else {
            shrink[0] = false;
        }
        return true;
    }

    private void removeFromLeaf(BNode<E> node, int idx) {
        for (int i = idx; i < node.count - 1; i++) {
            node.keys.set(i, node.keys.get(i + 1));
        }
        node.keys.set(node.count - 1, null);
        node.count--;
    }

    private void adjust(BNode<E> node, int pos, boolean[] shrink) {
        int minKeys = (orden - 1) / 2;
        BNode<E> left = (pos > 0) ? node.childs.get(pos - 1) : null;
        BNode<E> curr = node.childs.get(pos);
        BNode<E> right = (pos < node.count) ? node.childs.get(pos + 1) : null;

        // Intentar redistribuir con hermano izquierdo
        if (left != null && left.count > minKeys) {
            // Mover una clave del nodo padre hacia el hijo actual desde la izquierda
            for (int i = curr.count; i > 0; i--) {
                curr.keys.set(i, curr.keys.get(i - 1));
                curr.childs.set(i + 1, curr.childs.get(i));
            }
            curr.childs.set(1, curr.childs.get(0));
            curr.keys.set(0, node.keys.get(pos - 1));
            curr.childs.set(0, left.childs.get(left.count));
            curr.count++;

            node.keys.set(pos - 1, left.keys.get(left.count - 1));
            left.keys.set(left.count - 1, null);
            left.childs.set(left.count, null);
            left.count--;

            shrink[0] = false;
        }

        // Intentar redistribuir con hermano derecho
        else if (right != null && right.count > minKeys) {
            curr.keys.set(curr.count, node.keys.get(pos));
            curr.childs.set(curr.count + 1, right.childs.get(0));
            curr.count++;

            node.keys.set(pos, right.keys.get(0));
            for (int i = 0; i < right.count - 1; i++) {
                right.keys.set(i, right.keys.get(i + 1));
                right.childs.set(i, right.childs.get(i + 1));
            }
            right.childs.set(right.count - 1, right.childs.get(right.count));
            right.keys.set(right.count - 1, null);
            right.childs.set(right.count, null);
            right.count--;

            shrink[0] = false;
        }

        // Fusión con hermano izquierdo
        else if (left != null) {
            left.keys.set(left.count, node.keys.get(pos - 1));
            left.count++;
            for (int i = 0; i < curr.count; i++) {
                left.keys.set(left.count, curr.keys.get(i));
                left.childs.set(left.count, curr.childs.get(i));
                left.count++;
            }
            left.childs.set(left.count, curr.childs.get(curr.count));
            // Eliminar referencia del padre
            for (int i = pos - 1; i < node.count - 1; i++) {
                node.keys.set(i, node.keys.get(i + 1));
                node.childs.set(i + 1, node.childs.get(i + 2));
            }
            node.keys.set(node.count - 1, null);
            node.childs.set(node.count, null);
            node.count--;

            shrink[0] = node != root && node.count < minKeys;
        }

        // Fusión con hermano izquierdo
        else if (left != null) {
            left.keys.set(left.count, node.keys.get(pos - 1));
            left.count++;
            for (int i = 0; i < curr.count; i++) {
                left.keys.set(left.count, curr.keys.get(i));
                left.childs.set(left.count, curr.childs.get(i));
                left.count++;
            }
            left.childs.set(left.count, curr.childs.get(curr.count));
            // Eliminar referencia del padre
            for (int i = pos - 1; i < node.count - 1; i++) {
                node.keys.set(i, node.keys.get(i + 1));
                node.childs.set(i + 1, node.childs.get(i + 2));
            }
            node.keys.set(node.count - 1, null);
            node.childs.set(node.count, null);
            node.count--;

            shrink[0] = node != root && node.count < minKeys;
        }
    }

    // Excepción personalizada
    public class ItemNoFound extends Exception {
        public ItemNoFound(String message) {
            super(message);
        }
    }

    public static BTree<Integer> building_Btree(String filename) throws ItemNoFound {
        Map<Integer, BNode<Integer>> nodeMap = new HashMap<>();
        Map<Integer, Integer> levelMap = new HashMap<>();
        Map<Integer, List<Integer>> childrenByParent = new HashMap<>();
        int orden = 0;
        int rootId = -1;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNum = 0;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                if (lineNum == 0) {
                    orden = Integer.parseInt(line);
                    lineNum++;
                    continue;
                }

                // Parsear línea: nivel, idNodo, claves...
                String[] parts = line.split(",");
                if (parts.length < 3) throw new ItemNoFound("Formato incorrecto en el archivo.");

                int nivel = Integer.parseInt(parts[0].replaceAll("\\D", "")); // 2, 1, 0...
                int idNodo = Integer.parseInt(parts[1]);
                List<Integer> claves = new ArrayList<>();
                for (int i = 2; i < parts.length; i++) {
                    claves.add(Integer.parseInt(parts[i]));
                }
                BNode<Integer> node = new BNode<>(orden);
                node.idNode = idNodo; // Debes permitir en tu BNode setear el idNode manualmente para reconstrucción
                node.count = claves.size();
                for (int i = 0; i < claves.size(); i++) node.keys.set(i, claves.get(i));
                nodeMap.put(idNodo, node);
                levelMap.put(idNodo, nivel);
                if (nivel == 0) rootId = idNodo;
            }
        } catch (IOException | NumberFormatException e) {
            throw new ItemNoFound("Error al leer el archivo: " + e.getMessage());
        }
        
    }

    private String writeTree(BNode<E> current, Integer idPadre) {
        if (current == null) return "";

        // Preparar claves y lista de hijos no-nulos
        String claves = formatClaves(current);
        String padre = (idPadre == null) ? "--" : String.format("[%d]", idPadre);
        String hijos = formatHijos(current);

        // Formateo tipo tabla, puedes ajustar los anchos de columna si lo deseas
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-8d %-20s %-10s %-15s\n", current.idNode, claves, padre, hijos));

        // Recorrer hijos (solo los no-nulos), recursivo
        for (int i = 0; i <= current.count; i++) {
            BNode<E> child = current.childs.get(i);
            if (child != null) {
                sb.append(writeTree(child, current.idNode));
            }
        }
        return sb.toString();
    }

    private String formatClaves(BNode<E> node) {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < node.count; i++) {
            sb.append(node.keys.get(i));
            if (i < node.count - 1) sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    private String formatHijos(BNode<E> node) {
        StringBuilder sb = new StringBuilder("[");
        boolean primero = true;
        for (int i = 0; i <= node.count; i++) {
            BNode<E> child = node.childs.get(i);
            if (child != null) {
                if (!primero) sb.append(", ");
                sb.append(child.idNode);
                primero = false;
            }
        }
        sb.append("]");
        // Si no hay hijos, dejar vacío entre corchetes
        if (sb.length() == 2) return "";
        return sb.toString();
    }

    @Override
    public String toString() {
        String s = "";
        if (isEmpty())
            s += "BTree is empty...";
        else {
            // Encabezado de la tabla, puedes modificar el formato si prefieres tabulaciones o alineación
            s += String.format("%-8s %-20s %-10s %-15s\n", "Id.Nodo", "Claves Nodo", "Id.Padre", "Id.Hijos");
            s += writeTree(this.root, null);
        }
        return s;
    }
}
