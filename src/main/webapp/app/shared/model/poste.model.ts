import { IBureau } from 'app/shared/model//bureau.model';
import { IPersonne } from 'app/shared/model//personne.model';

export interface IPoste {
    id?: number;
    name?: string;
    quotePart?: number;
    place?: IBureau;
    habilitation?: IPersonne;
}

export class Poste implements IPoste {
    constructor(
        public id?: number,
        public name?: string,
        public quotePart?: number,
        public place?: IBureau,
        public habilitation?: IPersonne
    ) {}
}
