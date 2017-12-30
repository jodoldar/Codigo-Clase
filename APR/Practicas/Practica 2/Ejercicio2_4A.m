% Creacion e inicializacon de la red bayesiana original

N=4; C=1; S=2; R=3; W=4;
grafo = zeros(N,N);
grafo(C,[R S])=1;
grafo(R,W)=1;
grafo(S,W)=1;
nodosDiscretos = 1:N;
tallaNodos = 2*ones(1,N);
redB = mk_bnet(grafo,tallaNodos, 'discrete', nodosDiscretos);
redB.CPD{W} = tabular_CPD(redB,W,[1.0 0.1 0.1 0.01 0.0 0.9 0.9 0.99]);
redB.CPD{C} = tabular_CPD(redB,C,[0.5 0.5]);
redB.CPD{S} = tabular_CPD(redB,S,[0.5 0.9 0.5 0.1]);
redB.CPD{R} = tabular_CPD(redB,R,[0.8 0.2 0.2 0.8]);

% Obtencion de las muestras de ejemplo
nsamples = 10000;
samples = cell(N, nsamples);
for i=1:nsamples
    samples(:,i)=sample_bnet(redB);
end

% Estimación de la nueva red
nuevaRed = mk_bnet(grafo, tallaNodos);
seed = 0;
rand('state',seed);
nuevaRed.CPD{C} = tabular_CPD(nuevaRed, C);
nuevaRed.CPD{R} = tabular_CPD(nuevaRed, R);
nuevaRed.CPD{S} = tabular_CPD(nuevaRed, S);
nuevaRed.CPD{W} = tabular_CPD(nuevaRed, W);
nuevaRed2 = learn_params(nuevaRed,samples);

% Mostrar los parametros de la nueva red
CPTaux = cell(1,N);
for i=1:N
    s = struct(nuevaRed2.CPD{i});
    CPTaux{i} = s.CPT;
end
fprintf('Aprendizaje a partir de datos completos: \n');
fprintf('C: \n');
dispcpt(CPTaux{C});
fprintf('S: \n');
dispcpt(CPTaux{S});
fprintf('R: \n');
dispcpt(CPTaux{R});
fprintf('W: \n');
dispcpt(CPTaux{W});

% Nuevo conjunto de aprendizaje
samples2 = samples;
hide = rand(N, nsamples) > 0.5;
[I,J] = find(hide);
for k=1:length(I)
    samples2{I(k), J(k)} = [];
end

% Estimación de la nueva red
nuevaRedEM = mk_bnet(grafo, tallaNodos);
seed = 0;
rand('state',seed);
nuevaRedEM.CPD{C} = tabular_CPD(nuevaRedEM, C);
nuevaRedEM.CPD{R} = tabular_CPD(nuevaRedEM, R);
nuevaRedEM.CPD{S} = tabular_CPD(nuevaRedEM, S);
nuevaRedEM.CPD{W} = tabular_CPD(nuevaRedEM, W);

% Aprendizaje por EM
engine = jtree_inf_engine(nuevaRedEM);
max_iter = 100;
[nuevaRedEM2, LLtrace] = learn_params_em(engine, samples2, max_iter);

% Mostrar los datos de la nueva red
CPTaux2 = cell(1,N);
for i=1:N
    s = struct(nuevaRedEM2.CPD{i});
    CPTaux2{i} = s.CPT;
end
fprintf('Aprendizaje con datos incompletos mediante EM\n');
fprintf('C: \n');
dispcpt(CPTaux2{C});
fprintf('S: \n');
dispcpt(CPTaux2{S});
fprintf('R: \n');
dispcpt(CPTaux2{R});
fprintf('W: \n');
dispcpt(CPTaux2{W});