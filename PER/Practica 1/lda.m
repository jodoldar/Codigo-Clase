function[W]=lda(X,Xl,k)
    [F,C]=size(X);
    %%Calcular la media de los datos
    m = mean(X,2);
    Sw = zeros(F,F);
    Sb = zeros(F,F);
    Nc = length(unique(Xl));
    %% El proceso se repite para cada clase, arrastrando los resultados en Sw y Sb
    for c=unique(Xl)
        %%Calcular la media de la clase
        indices = Xl==c;
        Xc=X(:,indices);
        MediaClase=mean(Xc,2);
        %%Calcular la covarianza de la clase
        CovClase = cov(Xc');
        %%Calculo de las matrices intra-clase y entre-clase
        Sw=Sw+CovClase;
        Sb=Sb+(Nc*(MediaClase-m)*(MediaClase-m)');
    end
    [Vec,Lam] = eig(Sb,Sw);
    [V,I] = sort(diag(Lam),'descend');
    Eigen = Vec(:,I);
    %% Se eligen los k primeros eigenvectores
    W = Eigen(:,1:k);
end