package bstreelinklistinterfgeneric;
import Actividad2.QueueLink;
import Exceptions.ExceptionIsEmpty;
import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;
import bstreeInterface.BinarySearchTree;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E>{

    class Node{
        public E data;
        public Node left;
        public Node right;

        public Node(E data){
            this(data,null,null);
        }

        public Node(E data, Node left, Node Right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public Node root;

    public LinkedBST(){
        this.root = null;
    }

    public void insert(E data) throws ItemDuplicated {
        if (root == null) {
            root = new Node(data);
            return;
        }

        Node actual = root;
        Node padre = null;

        while (actual != null) {
            padre = actual;
            int comparacion = data.compareTo(actual.data);
            if (comparacion == 0) {
                throw new ItemDuplicated("El elemento ya existe en el árbol");
            } else if (comparacion < 0) {
                actual = actual.left;
            } else {
                actual = actual.right;
            }
        }

        if (data.compareTo(padre.data) < 0) {
            padre.left = new Node(data);
        } else {
            padre.right = new Node(data);
        }
    }

    public E search(E data) throws ItemNoFound {
        Node actual = root;

        while (actual != null) {
            int comparacion = data.compareTo(actual.data);

            if (comparacion == 0) {
                return actual.data;
            } else if (comparacion < 0) {
                actual = actual.left;
            } else {
                actual = actual.right;
            }
        }

        throw new ItemNoFound("El elemento no se encuentra en el árbol");
    }

    public void delete(E data) throws ExceptionIsEmpty {
        if (root == null) {
            throw new ExceptionIsEmpty("El árbol está vacío, no se puede eliminar");
        }

        Node actual = root;
        Node padre = null;

        while (actual != null && data.compareTo(actual.data) != 0) {
            padre = actual;
            if (data.compareTo(actual.data) < 0) {
                actual = actual.left;
            } else {
                actual = actual.right;
            }
        }

        if (actual == null) {
            return; // No se encontró el dato, no se hace nada
        }

        if (actual.left == null && actual.right == null) {
            if (actual == root) {
                root = null;
            } else if (padre.left == actual) {
                padre.left = null;
            } else {
                padre.right = null;
            }
        } else if (actual.left != null && actual.right == null) {
            if (actual == root) {
                root = actual.left;
            } else if (padre.left == actual) {
                padre.left = actual.left;
            } else {
                padre.right = actual.left;
            }
        } else if (actual.left == null && actual.right != null) {
            if (actual == root) {
                root = actual.right;
            } else if (padre.left == actual) {
                padre.left = actual.right;
            } else {
                padre.right = actual.right;
            }
        } else {
            Node sucesor = obtenerMinimo(actual.right);
            E datoSucesor = sucesor.data;
            delete(datoSucesor);
            actual.data = datoSucesor;
        }
    }

    private Node obtenerMinimo(Node nodo) {
        while (nodo.left != null) {
            nodo = nodo.left;
        }
        return nodo;
    }

    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public String toString() {
        return recorrerInorden(root);
    }

    private String recorrerInorden(Node nodo) {
        if (nodo == null) {
            return "";
        }
        String izquierda = recorrerInorden(nodo.left);
        String centro = nodo.data.toString() + " ";
        String derecha = recorrerInorden(nodo.right);
        return izquierda + centro + derecha;
    }

    public void inOrder(Node nodo) {
        if (nodo != null) {
            inOrder(nodo.left);
            System.out.println(nodo.data);
            inOrder(nodo.right);
        }
    }

    public void preOrder(Node nodo) {
        if (nodo != null) {
            System.out.println(nodo.data);
            preOrder(nodo.left);
            preOrder(nodo.right);
        }
    }

    public void postOrder(Node nodo) {
        if (nodo != null) {
            postOrder(nodo.left);
            postOrder(nodo.right);
            System.out.println(nodo.data);
        }
    }

    public E findMinNode(Node nodo) throws ItemNoFound {
        if (nodo == null) {
            throw new ItemNoFound("Subárbol vacío, no se puede encontrar el mínimo");
        }

        Node actual = nodo;
        while (actual.left != null) {
            actual = actual.left;
        }

        // Validar con search que el valor existe en el árbol completo
        return search(actual.data);
    }

    public E findMaxNode(Node nodo) throws ItemNoFound {
        if (nodo == null) {
            throw new ItemNoFound("Subárbol vacío, no se puede encontrar el máximo");
        }

        Node actual = nodo;
        while (actual.right != null) {
            actual = actual.right;
        }

        // Validar con search que el valor existe en el árbol completo
        return search(actual.data);
    }

    public void destroyNodes() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("El árbol está vacío, no hay nodos para eliminar");
        }
        root = null; // Al asignar null, el recolector de basura libera los nodos
    }

    public int countAllNodes() {
        return contarTodos(root);
    }

    private int contarTodos(Node nodo) {
        if (nodo == null) return 0;
        return 1 + contarTodos(nodo.left) + contarTodos(nodo.right);
    }

    public int countNodes() {
        return contarNodosNoHojas(root);
    }

    private int contarNodosNoHojas(Node nodo) {
        if (nodo == null) {
            return 0;
        }
        // Si es hoja (no tiene hijos), no se cuenta
        if (nodo.left == null && nodo.right == null) {
            return 0;
        }
        // Cuenta el nodo actual + los no hojas de subárboles
        return 1 + contarNodosNoHojas(nodo.left) + contarNodosNoHojas(nodo.right);
    }

    public int height(E x) {
        // Buscar el nodo que contiene el dato x
        Node nodo = root;
        while (nodo != null) {
            int cmp = ((Comparable<? super E>) x).compareTo(nodo.data);
            if (cmp == 0) {
                break;
            } else if (cmp < 0) {
                nodo = nodo.left;
            } else {
                nodo = nodo.right;
            }
        }
        // Si no se encontró el nodo, retornar -1
        if (nodo == null) return -1;

        // Calcular altura iterativa desde el nodo encontrado
        return calcularAlturaIterativa(nodo);
    }

    private int calcularAlturaIterativa(Node nodoRaiz) {
        if (nodoRaiz == null) return 0;

        QueueLink<Node> cola = new QueueLink<>();
        cola.enqueue(nodoRaiz);
        int altura = 0;

        while (!cola.isEmpty()) {
            int tamañoNivel = cola.size(); // Método size() que debes agregar en QueueLink
            for (int i = 0; i < tamañoNivel; i++) {
                try {
                    Node actual = cola.dequeue();
                    if (actual.left != null) cola.enqueue(actual.left);
                    if (actual.right != null) cola.enqueue(actual.right);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            altura++;
        }
        return altura - 1; // Altura en número de aristas
    }

    public int amplitude() {
        int altura = height(root.data);
        if (altura == -1) return 0;

        int maxAnchura = 0;
        for (int nivel = 0; nivel <= altura; nivel++) {
            int anchuraNivel = contarNodosEnNivel(root, nivel);
            if (anchuraNivel > maxAnchura) {
                maxAnchura = anchuraNivel;
            }
        }
        return maxAnchura;
    }

    private int contarNodosEnNivel(Node nodo, int nivel) {
        if (nodo == null) return 0;
        if (nivel == 0) return 1;
        return contarNodosEnNivel(nodo.left, nivel - 1) + contarNodosEnNivel(nodo.right, nivel - 1);
    }

    public int areaBST() {
        if (root == null) return 0;

        int hojas = 0;
        int altura = height(root.data);

        QueueLink<Node> cola = new QueueLink<>();
        cola.enqueue(root);

        while (!cola.isEmpty()) {
            Node actual = null;
            try {
                actual = cola.dequeue();
            } catch (Actividad1.ExceptionIsEmpty e) {
                // No debería ocurrir, ya que controlamos isEmpty antes
                return 0; // o puedes lanzar RuntimeException si prefieres
            }

            if (actual.left == null && actual.right == null) {
                hojas++;
            }
            if (actual.left != null) cola.enqueue(actual.left);
            if (actual.right != null) cola.enqueue(actual.right);
        }

        return hojas * altura;
    }

    public void drawBST() {
        if (root == null) {
            System.out.println("Árbol vacío");
        } else {
            drawBSTRec(root, "", true);
        }
    }

    private void drawBSTRec(Node nodo, String prefijo, boolean esUltimo) {
        if (nodo != null) {
            System.out.println(prefijo + (esUltimo ? "└── " : "├── ") + nodo.data);
            drawBSTRec(nodo.left, prefijo + (esUltimo ? "    " : "│   "), false);
            drawBSTRec(nodo.right, prefijo + (esUltimo ? "    " : "│   "), true);
        }
    }

    public void parenthesize() {
        mostrarConParentesis(root, 0);
    }

    private void mostrarConParentesis(Node nodoActual, int nivel) {
        if (nodoActual == null) {
            return;
        }

        StringBuilder sangria = new StringBuilder();
        for (int i = 0; i < nivel; i++) {
            sangria.append("  ");
        }

        System.out.print(sangria.toString() + nodoActual.data);

        boolean tieneHijos = nodoActual.left != null || nodoActual.right != null;

        if (tieneHijos) {
            System.out.println(" (");
            mostrarConParentesis(nodoActual.left, nivel + 1);
            mostrarConParentesis(nodoActual.right, nivel + 1);
            System.out.print(sangria.toString() + ")");
        }

        if (nivel == 0) {
            System.out.println();
        } else {
            System.out.println();
        }
    }
}