import { IPoste } from 'app/shared/model//poste.model';

export interface IPersonne {
    id?: number;
    name?: string;
    client?: string;
    imageContentType?: string;
    image?: any;
    travails?: IPoste[];
}

export class Personne implements IPersonne {
    constructor(
        public id?: number,
        public name?: string,
        public client?: string,
        public imageContentType?: string,
        public image?: any,
        public travails?: IPoste[]
    ) {}
}
