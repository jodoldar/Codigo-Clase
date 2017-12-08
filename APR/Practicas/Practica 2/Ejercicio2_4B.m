% Creacion e inicialización de la red bayesiana
N = 5;
P = 1; F = 2; C = 3; X = 4; D = 5;
grafo = zeros(N,N);
grafo(P,C) = 1;
grafo(F,C) = 1;
grafo(C,[X D]) = 1;
nodosDiscretos = 1:N;
tallaNodos =[2 2 2 3 2];
redB = mk_bnet(grafo,tallaNodos, 'discrete', nodosDiscretos);
% Polucion - P
% b (bajo) - 1 - False
% a (alto) - 2 - True
redB.CPD{P} = tabular_CPD(redB,P,[0.9 0.1]);
% Fumador - F
% n (no) - 1 - False
% s (si) - 2 - True
redB.CPD{F} = tabular_CPD(redB,F,[0.7 0.3]);
% Cancer - C
% n (negativo) - 1 - False
% p (positivo) - 2 - True
redB.CPD{C} = tabular_CPD(redB,C,[0.999 0.95 0.97 0.92 0.001 0.05 0.03 0.08]);
% Rayos X - X
% n (negativo) - 1
% d (dudoso) - 2
% p (positivo) - 3
redB.CPD{X} = tabular_CPD(redB,X,[0.8 0.1 0.1 0.2 0.1 0.7]);
% Disnea - D
% n (no) - 1 - False
% s (si) - 2 - True
redB.CPD{D} = tabular_CPD(redB,D,[0.7 0.35 0.3 0.65]);

% Añadir evidencias 1
engine = jtree_inf_engine(redB);
evidencia = cell(1,N);
evidencia{F} = 1;
evidencia{X} = 1;
evidencia{D} = 2;
[engine,~] = enter_evidence(engine,evidencia);
m = marginal_nodes(engine,C);
fprintf('1.- Probabilidad de que un paciente no fumador no \ntenga cancer de pulmon si la radiografia ha dado\nun resultado negativo pero sufre disnea: \n');
disp(m.T);

% Evidencias 2
engine = jtree_inf_engine(redB);
evidencia = cell(1,N);
evidencia{C} = 2;
[explMasProbable,logVer] = calc_mpe(engine,evidencia);
fprintf('2.- Explicacion más probable de que un paciente sufra \ncancer de pulmon: \n');
disp(explMasProbable);
disp(logVer);