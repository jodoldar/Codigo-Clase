equis = logspace(1,3,10);
numelem = length(equis);
listError = zeros(numelem,1);
parfor i=1:numelem
    x = equis(i);
    x = round(x);
    listError(i) = clasificationError('./data/usps/tr.dat','./data/usps/trlabels.dat','./data/usps/ts.dat','./data/usps/tslabels.dat',x);
end
base = 1:numelem;
plot(base,listError);