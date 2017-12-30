function [ error,variance ] = clasificationError(trPath,trLabelsPath,tsPath,tsLabelsPath,numGaus)
%clasificationError
%   This function obtains the classification error of a data
%   set using bayesian networks and gaussian mixtures.

    % Load and inicialization of the training data
    datApr = load(trPath,'-ascii');
    etqApr = load(trLabelsPath,'-ascii');
    dataApr = zscore(datApr);
    etiqApr = etqApr + 1;
    [numVec, dim] = size(dataApr);
    numClas = max(etiqApr);
    
    % Data and inicialization of graph
    grafo = [0 1 1; 0 0 1; 0 0 0];
    numNodos = length(grafo);
    tallaNodos = [numClas numGaus dim];
    nodosDiscretos = [1 2];
    redB = mk_bnet(grafo, tallaNodos, 'discrete', nodosDiscretos);
    redB.CPD{1} = tabular_CPD(redB,1);
    redB.CPD{2} = tabular_CPD(redB,2);
    redB.CPD{3} = gaussian_CPD(redB,3,'cov_type','diag');
    
    % Learning of the probailities of the problem
    datosApr = cell(numNodos,numVec);
    datosApr(numNodos,:) = num2cell(dataApr',1);
    datosApr(1,:) = num2cell(etiqApr',1);
    motor = jtree_inf_engine(redB);
    maxIter = 15;
    [~,~,motor2] = learn_params_em(motor,datosApr,maxIter);

    % Load and inicialization of the test data
    datTest = load(tsPath,'-ascii');
    etqTest = load(tsLabelsPath,'-ascii');
    dataTest = zscore(datTest);
    etiqTest = etqTest + 1;
    numTestElem = length(dataTest);
    
    % Classification of the test data
    p = zeros(numTestElem,numClas);
    evidencia = cell(numNodos,1);
    for i=1:numTestElem
        evidencia{numNodos} = dataTest(i,:)';
        [motor3,~] = enter_evidence(motor2,evidencia);
        m = marginal_nodes(motor3,1);
        p(i,:) = m.T;
    end
    
    I = zeros(numTestElem,1);
    for i=1:numTestElem
        [~,I(i)] = max(p(i,:));
    end

    correct = sum(I==etiqTest);
    error = (numTestElem-correct)/numTestElem;
    variance = 1.96*sqrt((error*(1-error))/numTestElem);
end

