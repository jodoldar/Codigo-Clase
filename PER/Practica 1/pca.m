function[m,W]=pca(X,k)
    [F,C] = size(X);
    %% Calculo de la media de los datos
    m = mean(X);
    onesFil = ones(1,F);
    auxMean = onesFil'*m;
    %% Resta a los datos originales de la media
    xAux = X-auxMean;
    %% Calculo de la covarianza
    covX2 = cov(xAux');
    [Vec2,Lam2]=eig(covX2);
    [V2,I2] = sort(diag(Lam2),'descend');
    Eigen2 = Vec2(:,I2);
    %% Se eligen los k primeros eigenvectores
    W = Eigen2(:,1:k);
end