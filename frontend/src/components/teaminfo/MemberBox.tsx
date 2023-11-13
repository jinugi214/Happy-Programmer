import "./MemberBox.css"

type MemberBoxProps = {
  imgName: string;
  name: string;
  position: string;
};

function MemberBox({ imgName, name, position }: MemberBoxProps) {
  const imgUrl = process.env.PUBLIC_URL + "/imgs/" + imgName + ".png";

  return (
    <div className="member-box">
      <div className="member-content-one">
        <img src={imgUrl} alt={name} className="member-img" />
      </div>
      <div className="hover-text">하고싶은 말</div>
      <div className="member-content-two">
        <div className="text-s mb-4">{name}</div>
        <div>{position}</div>
      </div>
    </div>
  );
}

export default MemberBox;
