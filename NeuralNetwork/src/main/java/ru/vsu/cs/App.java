package ru.vsu.cs;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        double err;
        int sample = 0, iterations = 0;
        int sum = 0;
        NeuralNetwork nn = new NeuralNetwork();
        //
        nn.assignRandomWeights();
        while (true){
            if(sample == Samples.MAX_SAMPLES)
                sample= 0;
            System.out.println(sample);
            nn.inputs[0] = Samples.samples[sample].health;
            nn.inputs[1] = Samples.samples[sample].actionPoints;
            nn.inputs[2] = Samples.samples[sample].Grenade;
            nn.inputs[3] = Samples.samples[sample].Medkit;
            nn.inputs[4] = Samples.samples[sample].Med;
            nn.inputs[5] = Samples.samples[sample].Eat;
            nn.inputs[6] = Samples.samples[sample].Enemy;

            nn.target[0] = Samples.samples[sample].out[0];
            nn.target[1] = Samples.samples[sample].out[1];
            nn.target[2] = Samples.samples[sample].out[2];
            nn.target[3] = Samples.samples[sample].out[3];
            nn.target[4] = Samples.samples[sample].out[4];
            nn.target[5] = Samples.samples[sample].out[5];

            nn.feedForward();

            err = 0.0;
            for(int i = 0; i < nn.OUTPUT; i++){
                double tmp = Samples.samples[sample].out[i]-nn.actual[i];
                err += (tmp*tmp);
            }
            err = err*0.5;
            System.out.println(err);
            if(iterations++>1000000) break;
            sample++;
            nn.backPropagate();
        } //end while
         //проверить сеть
        for(int i = 0; i<Samples.MAX_SAMPLES; i++){
            nn.inputs[0] = Samples.samples[sample].health;
            nn.inputs[1] = Samples.samples[sample].actionPoints;
            nn.inputs[2] = Samples.samples[sample].Grenade;
            nn.inputs[3] = Samples.samples[sample].Medkit;
            nn.inputs[4] = Samples.samples[sample].Med;
            nn.inputs[5] = Samples.samples[sample].Eat;
            nn.inputs[6] = Samples.samples[sample].Enemy;

            nn.target[0] = Samples.samples[sample].out[0];
            nn.target[1] = Samples.samples[sample].out[1];
            nn.target[2] = Samples.samples[sample].out[2];
            nn.target[3] = Samples.samples[sample].out[3];
            nn.target[4] = Samples.samples[sample].out[4];
            nn.target[5] = Samples.samples[sample].out[5];

            nn.feedForward();

            if(nn.action(nn.actual) != nn.action(nn.target)){
               System.out.printf("%g : %g : %g : %g : %s (%s)",
                       nn.inputs[0], nn.inputs[1],
                       nn.inputs[2], nn.inputs[3],
                       nn.strings[nn.action(nn.actual)],
                       nn.strings[nn.action(nn.target)]);
            }else
                sum++;
        }

        System.out.printf("Network is %g%% correct \n",
                (float) sum / Samples.MAX_SAMPLES * 100.0);

        // Здоровье нож пистолет враг
        nn.inputs[0] = 2.0;
        nn.inputs[1] = 1.0;
        nn.inputs[2] = 1.0;
        nn.inputs[3] = 1.0;
        nn.feedForward();
        System.out.printf("2111 Action %s\n", nn.strings[nn.action(nn.actual)]);

        nn.inputs[0] = 1.0;
        nn.inputs[1] = 1.0;
        nn.inputs[2] = 1.0;
        nn.inputs[3] = 2.0;
        nn.feedForward();
        System.out.printf("1112 Action %s\n", nn.strings[nn.action(nn.actual)]);

        nn.inputs[0] = 0.0;
        nn.inputs[1] = .0;
        nn.inputs[2] = .0;
        nn.inputs[3] = .0;
        nn.feedForward();
        System.out.printf("0000 Action %s\n", nn.strings[nn.action(nn.actual)]);

        nn.inputs[0] = .0;
        nn.inputs[1] = 1.0;
        nn.inputs[2] = 1.0;
        nn.inputs[3] = 1.0;
        nn.feedForward();
        System.out.printf("0111 Action %s\n", nn.strings[nn.action(nn.actual)]);

        nn.inputs[0] = 2.0;
        nn.inputs[1] = .0;
        nn.inputs[2] = 1.0;
        nn.inputs[3] = 3.0;
        nn.feedForward();
        System.out.printf("2013 Action %s\n", nn.strings[nn.action(nn.actual)]);

        nn.inputs[0] = 2.0;
        nn.inputs[1] = 1.0;
        nn.inputs[2] = .0;
        nn.inputs[3] = 3.0;
        nn.feedForward();
        System.out.printf("2103 Action %s\n", nn.strings[nn.action(nn.actual)]);

        nn.inputs[0] = .0;
        nn.inputs[1] = 1.0;
        nn.inputs[2] = .0;
        nn.inputs[3] = 3.0;
        nn.feedForward();
        System.out.printf("0103 Action %s\n", nn.strings[nn.action(nn.actual)]);
    }
}
























