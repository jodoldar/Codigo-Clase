parfor i=37:49
    [aux1,aux2] = clasificationError('./data/usps/tr.dat','./data/usps/trlabels.dat','./data/usps/ts.dat','./data/usps/tslabels.dat',i);
    error(i,:)=aux1,aux2;
    %error(i,2)=aux2;
    fprintf('Acabada iteracion numero %d',i);
end
for i=37:49
    error(i,2)=0.0141;
end
errorbar(1:46,error(:,1),error(:,2));