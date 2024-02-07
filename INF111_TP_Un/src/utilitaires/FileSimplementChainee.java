package utilitaires;

public class FileSimplementChainee {

    int cle;
    FileSimplementChainee next;

   
        public FileSimplementChainee(int cle) { //constructueur pour cree une nouvelle fille

            this.cle = cle;
            this.next = null;
        }


        void enQueue(int cle) {

            // Create a new LL node
            FileSimplementChainee temp = new FileSimplementChainee(cle);

            // If queue is empty, then new node is front and
            // rear both
            if (this.rear == null) {
                this.front = this.rear = temp;
                return;
            }

            // Add the new node at the end of queue and change
            // rear
            this.rear.next = temp;
            this.rear = temp;
        }
    }
}
