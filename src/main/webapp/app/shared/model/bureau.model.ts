import { IPoste } from 'app/shared/model//poste.model';

export interface IBureau {
    id?: number;
    name?: string;
    espace?: string;
    postes?: IPoste[];
}

export class Bureau implements IBureau {
    constructor(public id?: number, public name?: string, public espace?: string, public postes?: IPoste[]) {}
}
