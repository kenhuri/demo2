import { IPersonne } from 'app/shared/model//personne.model';

export interface IBureau {
    id?: number;
    name?: string;
    espace?: string;
    place?: IPersonne;
}

export class Bureau implements IBureau {
    constructor(public id?: number, public name?: string, public espace?: string, public place?: IPersonne) {}
}
